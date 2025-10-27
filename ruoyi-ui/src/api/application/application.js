import request from '@/utils/request'

// 查询请假申请列表
export function listApplication(query) {
  return request({
    url: '/application/application/list',
    method: 'get',
    params: query
  })
}

// 查询请假申请详细
export function getApplication(leaveId) {
  return request({
    url: '/application/application/' + leaveId,
    method: 'get'
  })
}

// 新增请假申请
export function addApplication(data) {
  return request({
    url: '/application/application',
    method: 'post',
    data: data
  })
}

// 修改请假申请
export function updateApplication(data) {
  return request({
    url: '/application/application',
    method: 'put',
    data: data
  })
}

// 删除请假申请
export function delApplication(leaveId) {
  return request({
    url: '/application/application/' + leaveId,
    method: 'delete'
  })
}
