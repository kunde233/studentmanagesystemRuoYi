<template>
  <div class="app-container">
    <el-form :model="queryParams" ref="queryRef" :inline="true" v-show="showSearch" label-width="68px">
      <el-form-item label="请假类型" prop="leaveType">
        <el-input
          v-model="queryParams.leaveType"
          placeholder="请输入请假类型"
          clearable
          @keyup.enter="handleQuery"
        />
      </el-form-item>
      <el-form-item label="开始时间" prop="startDatetime">
        <el-date-picker clearable
          v-model="queryParams.startDatetime"
          type="date"
          value-format="YYYY-MM-DD"
          placeholder="请选择开始时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item label="结束时间" prop="endDatetime">
        <el-date-picker clearable
          v-model="queryParams.endDatetime"
          type="date"
          value-format="YYYY-MM-DD"
          placeholder="请选择结束时间">
        </el-date-picker>
      </el-form-item>
      <el-form-item>
        <el-button type="primary" icon="Search" @click="handleQuery">搜索</el-button>
        <el-button icon="Refresh" @click="resetQuery">重置</el-button>
      </el-form-item>
    </el-form>

    <el-row :gutter="10" class="mb8">
      <el-col :span="1.5">
        <el-button
          type="primary"
          plain
          icon="Plus"
          @click="handleAdd"
          v-hasPermi="['application:application:add']"
        >新增</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="success"
          plain
          icon="Edit"
          :disabled="single"
          @click="handleUpdate"
          v-hasPermi="['application:application:edit']"
        >修改</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="danger"
          plain
          icon="Delete"
          :disabled="multiple"
          @click="handleDelete"
          v-hasPermi="['application:application:remove']"
        >删除</el-button>
      </el-col>
      <el-col :span="1.5">
        <el-button
          type="warning"
          plain
          icon="Download"
          @click="handleExport"
          v-hasPermi="['application:application:export']"
        >导出</el-button>
      </el-col>
      <right-toolbar v-model:showSearch="showSearch" @queryTable="getList"></right-toolbar>
    </el-row>

    <el-table v-loading="loading" :data="applicationList" @selection-change="handleSelectionChange">
      <el-table-column type="selection" width="55" align="center" />
      <el-table-column label="请假申请ID" align="center" prop="leaveId" />
      <el-table-column label="请假类型" align="center" prop="leaveType" />
      <el-table-column label="开始时间" align="center" prop="startDatetime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.startDatetime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="结束时间" align="center" prop="endDatetime" width="180">
        <template #default="scope">
          <span>{{ parseTime(scope.row.endDatetime, '{y}-{m}-{d}') }}</span>
        </template>
      </el-table-column>
      <el-table-column label="请假事由" align="center" prop="leaveReason" />
      <el-table-column label="审批状态" align="center" prop="approvalStatus" />
      <el-table-column label="当前审批人角色" align="center" prop="currentApprover" />
      <el-table-column label="附件URL" align="center" prop="attachmentUrl" />
      <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
        <template #default="scope">
          <el-button link type="primary" icon="Edit" @click="handleUpdate(scope.row)" v-hasPermi="['application:application:edit']">修改</el-button>
          <el-button link type="primary" icon="Delete" @click="handleDelete(scope.row)" v-hasPermi="['application:application:remove']">删除</el-button>
        </template>
      </el-table-column>
    </el-table>
    
    <pagination
      v-show="total>0"
      :total="total"
      v-model:page="queryParams.pageNum"
      v-model:limit="queryParams.pageSize"
      @pagination="getList"
    />

    <!-- 添加或修改请假申请对话框 -->
    <el-dialog :title="title" v-model="open" width="500px" append-to-body>
      <el-form ref="applicationRef" :model="form" :rules="rules" label-width="80px">
        <el-form-item label="请假类型" prop="leaveType">
          <el-input v-model="form.leaveType" placeholder="请输入请假类型" />
        </el-form-item>
        <el-form-item label="开始时间" prop="startDatetime">
          <el-date-picker clearable
            v-model="form.startDatetime"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择开始时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="结束时间" prop="endDatetime">
          <el-date-picker clearable
            v-model="form.endDatetime"
            type="date"
            value-format="YYYY-MM-DD"
            placeholder="请选择结束时间">
          </el-date-picker>
        </el-form-item>
        <el-form-item label="请假事由" prop="leaveReason">
          <el-input v-model="form.leaveReason" type="textarea" placeholder="请输入内容" />
        </el-form-item>
        <el-form-item label="附件URL" prop="attachmentUrl">
          <el-input v-model="form.attachmentUrl" placeholder="请输入附件URL" />
        </el-form-item>
      </el-form>
      <template #footer>
        <div class="dialog-footer">
          <el-button type="primary" @click="submitForm">确 定</el-button>
          <el-button @click="cancel">取 消</el-button>
        </div>
      </template>
    </el-dialog>
  </div>
</template>

<script setup name="Application">
import { listApplication, getApplication, delApplication, addApplication, updateApplication } from "@/api/application/application"

const { proxy } = getCurrentInstance()

const applicationList = ref([])
const open = ref(false)
const loading = ref(true)
const showSearch = ref(true)
const ids = ref([])
const single = ref(true)
const multiple = ref(true)
const total = ref(0)
const title = ref("")

const data = reactive({
  form: {},
  queryParams: {
    pageNum: 1,
    pageSize: 10,
    leaveType: null,
    startDatetime: null,
    endDatetime: null,
  },
  rules: {
    leaveType: [
      { required: true, message: "请假类型不能为空", trigger: "blur" }
    ],
    startDatetime: [
      { required: true, message: "开始时间不能为空", trigger: "blur" }
    ],
    endDatetime: [
      { required: true, message: "结束时间不能为空", trigger: "blur" }
    ],
    leaveReason: [
      { required: true, message: "请假事由不能为空", trigger: "blur" }
    ],
    attachmentUrl: [
      { required: true, message: "附件URL不能为空", trigger: "blur" }
    ],
  }
})

const { queryParams, form, rules } = toRefs(data)

/** 查询请假申请列表 */
function getList() {
  loading.value = true
  listApplication(queryParams.value).then(response => {
    applicationList.value = response.rows
    total.value = response.total
    loading.value = false
  })
}

// 取消按钮
function cancel() {
  open.value = false
  reset()
}

// 表单重置
function reset() {
  form.value = {
    leaveId: null,
    userId: null,
    leaveType: null,
    startDatetime: null,
    endDatetime: null,
    leaveReason: null,
    approvalStatus: null,
    currentApprover: null,
    attachmentUrl: null,
    createBy: null,
    createTime: null,
    updateBy: null,
    updateTime: null
  }
  proxy.resetForm("applicationRef")
}

/** 搜索按钮操作 */
function handleQuery() {
  queryParams.value.pageNum = 1
  getList()
}

/** 重置按钮操作 */
function resetQuery() {
  proxy.resetForm("queryRef")
  handleQuery()
}

// 多选框选中数据
function handleSelectionChange(selection) {
  ids.value = selection.map(item => item.leaveId)
  single.value = selection.length != 1
  multiple.value = !selection.length
}

/** 新增按钮操作 */
function handleAdd() {
  reset()
  open.value = true
  title.value = "添加请假申请"
}

/** 修改按钮操作 */
function handleUpdate(row) {
  reset()
  const _leaveId = row.leaveId || ids.value
  getApplication(_leaveId).then(response => {
    form.value = response.data
    open.value = true
    title.value = "修改请假申请"
  })
}

/** 提交按钮 */
function submitForm() {
  proxy.$refs["applicationRef"].validate(valid => {
    if (valid) {
      if (form.value.leaveId != null) {
        updateApplication(form.value).then(response => {
          proxy.$modal.msgSuccess("修改成功")
          open.value = false
          getList()
        })
      } else {
        addApplication(form.value).then(response => {
          proxy.$modal.msgSuccess("新增成功")
          open.value = false
          getList()
        })
      }
    }
  })
}

/** 删除按钮操作 */
function handleDelete(row) {
  const _leaveIds = row.leaveId || ids.value
  proxy.$modal.confirm('是否确认删除请假申请编号为"' + _leaveIds + '"的数据项？').then(function() {
    return delApplication(_leaveIds)
  }).then(() => {
    getList()
    proxy.$modal.msgSuccess("删除成功")
  }).catch(() => {})
}

/** 导出按钮操作 */
function handleExport() {
  proxy.download('application/application/export', {
    ...queryParams.value
  }, `application_${new Date().getTime()}.xlsx`)
}

getList()
</script>
