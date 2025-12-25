-- ----------------------------
-- 人力资源管理系统数据库表
-- ----------------------------

-- ----------------------------
-- 1、职位表 hr_position
-- ----------------------------
drop table if exists hr_position;
create table hr_position (
  position_id       bigint(20)      not null auto_increment    comment '职位ID',
  position_code     varchar(64)     not null                   comment '职位编码',
  position_name     varchar(50)     not null                   comment '职位名称',
  dept_id           bigint(20)      not null                   comment '所属部门ID（三级机构）',
  position_sort     int(4)          default 0                  comment '显示顺序',
  status            char(1)         default '0'                comment '状态（0正常 1停用）',
  del_flag          char(1)         default '0'                comment '删除标志（0存在 2删除）',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time       datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  remark            varchar(500)    default null               comment '备注',
  primary key (position_id)
) engine=innodb auto_increment=100 comment = '职位表';

-- ----------------------------
-- 2、薪酬项目表 hr_salary_item
-- ----------------------------
drop table if exists hr_salary_item;
create table hr_salary_item (
  item_id           bigint(20)      not null auto_increment    comment '薪酬项目ID',
  item_code         varchar(64)     not null                   comment '项目编码',
  item_name         varchar(100)    not null                   comment '项目名称',
  item_type         char(1)         default '0'                comment '项目类型（0收入 1扣除）',
  item_sort         int(4)          default 0                  comment '显示顺序',
  status            char(1)         default '0'                comment '状态（0正常 1停用）',
  del_flag          char(1)         default '0'                comment '删除标志（0存在 2删除）',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time       datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  remark            varchar(500)    default null               comment '备注',
  primary key (item_id)
) engine=innodb auto_increment=100 comment = '薪酬项目表';

-- ----------------------------
-- 初始化薪酬项目数据
-- ----------------------------
insert into hr_salary_item values(1, 'base_salary', '基本工资', '0', 1, '0', '0', 'admin', sysdate(), '', null, '基本工资');
insert into hr_salary_item values(2, 'position_allowance', '岗位津贴', '0', 2, '0', '0', 'admin', sysdate(), '', null, '岗位津贴');
insert into hr_salary_item values(3, 'performance_bonus', '绩效奖金', '0', 3, '0', '0', 'admin', sysdate(), '', null, '绩效奖金');
insert into hr_salary_item values(4, 'overtime_pay', '加班费', '0', 4, '0', '0', 'admin', sysdate(), '', null, '加班费');
insert into hr_salary_item values(5, 'social_insurance', '社保扣款', '1', 5, '0', '0', 'admin', sysdate(), '', null, '社保扣款');
insert into hr_salary_item values(6, 'housing_fund', '公积金扣款', '1', 6, '0', '0', 'admin', sysdate(), '', null, '公积金扣款');
insert into hr_salary_item values(7, 'income_tax', '个人所得税', '1', 7, '0', '0', 'admin', sysdate(), '', null, '个人所得税');

-- ----------------------------
-- 3、员工档案表 hr_employee
-- ----------------------------
drop table if exists hr_employee;
create table hr_employee (
  employee_id       bigint(20)      not null auto_increment    comment '员工ID',
  employee_code     varchar(20)     not null                   comment '档案编号',
  employee_name     varchar(30)     not null                   comment '姓名',
  gender            char(1)         default '0'                comment '性别（0男 1女 2未知）',
  dept_id_first     bigint(20)      default null               comment '一级机构ID',
  dept_id_second    bigint(20)      default null               comment '二级机构ID',
  dept_id_third     bigint(20)      default null               comment '三级机构ID',
  position_id       bigint(20)      default null               comment '职位ID',
  title             varchar(20)     default null               comment '职称（初级/中级/高级）',
  salary_standard_id bigint(20)     default null               comment '薪酬标准ID',
  email             varchar(50)     default ''                 comment 'Email',
  phone             varchar(20)     default ''                 comment '电话',
  qq                varchar(20)     default ''                 comment 'QQ',
  mobile            varchar(20)     default ''                 comment '手机',
  address           varchar(200)    default ''                 comment '住址',
  post_code         varchar(10)     default ''                 comment '邮编',
  nationality       varchar(50)     default ''                 comment '国籍',
  birthplace        varchar(100)    default ''                 comment '出生地',
  birthday          date            default null               comment '出生日期',
  nation            varchar(30)     default ''                 comment '民族',
  religion          varchar(50)     default ''                 comment '宗教信仰',
  political_status  varchar(30)     default ''                 comment '政治面貌',
  id_card           varchar(20)     default ''                 comment '身份证号码',
  education         varchar(20)     default ''                 comment '学历',
  photo             varchar(200)    default ''                 comment '照片路径',
  resume            text            default null               comment '个人履历',
  family_info       text            default null               comment '家庭关系信息',
  status            char(1)         default '0'                comment '状态（0待复核 1正常 2已删除）',
  register_by       varchar(64)     default ''                 comment '登记人',
  register_time     datetime                                   comment '登记时间',
  reviewer          varchar(64)     default ''                 comment '复核人',
  review_time       datetime                                   comment '复核时间',
  del_flag          char(1)         default '0'                comment '删除标志（0存在 2删除）',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time       datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  remark            varchar(500)    default null               comment '备注',
  primary key (employee_id),
  unique key uk_employee_code (employee_code)
) engine=innodb auto_increment=100 comment = '员工档案表';

-- ----------------------------
-- 4、薪酬标准表 hr_salary_standard
-- ----------------------------
drop table if exists hr_salary_standard;
create table hr_salary_standard (
  standard_id       bigint(20)      not null auto_increment    comment '薪酬标准ID',
  standard_code     varchar(64)     not null                   comment '薪酬标准编号',
  standard_name     varchar(100)    not null                   comment '薪酬标准名称',
  position_id       bigint(20)      default null               comment '适用职位ID',
  title             varchar(20)     default null               comment '适用职称（初级/中级/高级）',
  total_amount      decimal(10,2)   default 0                  comment '薪酬总额',
  maker             varchar(64)     default ''                 comment '制定人',
  changer           varchar(64)     default ''                 comment '变更人',
  change_time       datetime                                   comment '变更时间',
  reviewer          varchar(64)     default ''                 comment '复核人',
  review_time       datetime                                   comment '复核时间',
  status            char(1)         default '0'                comment '状态（0待复核 1已复核 2停用）',
  del_flag          char(1)         default '0'                comment '删除标志（0存在 2删除）',
  create_by         varchar(64)     default ''                 comment '登记人',
  create_time       datetime                                   comment '登记时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  remark            varchar(500)    default null               comment '备注',
  primary key (standard_id)
) engine=innodb auto_increment=100 comment = '薪酬标准表';

-- ----------------------------
-- 5、薪酬标准明细表 hr_salary_standard_detail
-- ----------------------------
drop table if exists hr_salary_standard_detail;
create table hr_salary_standard_detail (
  detail_id         bigint(20)      not null auto_increment    comment '明细ID',
  standard_id       bigint(20)      not null                   comment '薪酬标准ID',
  item_id           bigint(20)      not null                   comment '薪酬项目ID',
  amount            decimal(10,2)   default 0                  comment '金额',
  primary key (detail_id)
) engine=innodb auto_increment=100 comment = '薪酬标准明细表';

-- ----------------------------
-- 6、薪酬发放记录表 hr_salary_record
-- ----------------------------
drop table if exists hr_salary_record;
create table hr_salary_record (
  record_id         bigint(20)      not null auto_increment    comment '发放记录ID',
  record_code       varchar(64)     not null                   comment '发放记录编号',
  employee_id       bigint(20)      not null                   comment '员工ID',
  standard_id       bigint(20)      default null               comment '薪酬标准ID',
  salary_month      varchar(10)     not null                   comment '发放月份（yyyy-MM）',
  base_amount       decimal(10,2)   default 0                  comment '标准金额',
  bonus_amount      decimal(10,2)   default 0                  comment '奖励金额',
  deduct_amount     decimal(10,2)   default 0                  comment '扣除金额',
  actual_amount     decimal(10,2)   default 0                  comment '实发金额',
  status            char(1)         default '0'                comment '状态（0待复核 1已复核）',
  reviewer          varchar(64)     default ''                 comment '复核人',
  review_time       datetime                                   comment '复核时间',
  del_flag          char(1)         default '0'                comment '删除标志（0存在 2删除）',
  create_by         varchar(64)     default ''                 comment '创建者',
  create_time       datetime                                   comment '创建时间',
  update_by         varchar(64)     default ''                 comment '更新者',
  update_time       datetime                                   comment '更新时间',
  remark            varchar(500)    default null               comment '备注',
  primary key (record_id)
) engine=innodb auto_increment=100 comment = '薪酬发放记录表';

-- ----------------------------
-- 7、薪酬发放明细表 hr_salary_record_detail
-- ----------------------------
drop table if exists hr_salary_record_detail;
create table hr_salary_record_detail (
  detail_id         bigint(20)      not null auto_increment    comment '明细ID',
  record_id         bigint(20)      not null                   comment '发放记录ID',
  item_id           bigint(20)      not null                   comment '薪酬项目ID',
  amount            decimal(10,2)   default 0                  comment '金额',
  primary key (detail_id)
) engine=innodb auto_increment=100 comment = '薪酬发放明细表';

-- ----------------------------
-- 添加字典类型
-- ----------------------------
insert into sys_dict_type values(100, '员工职称', 'hr_employee_title', '0', 'admin', sysdate(), '', null, '员工职称列表');
insert into sys_dict_type values(101, '员工学历', 'hr_employee_education', '0', 'admin', sysdate(), '', null, '员工学历列表');
insert into sys_dict_type values(102, '员工状态', 'hr_employee_status', '0', 'admin', sysdate(), '', null, '员工状态列表');
insert into sys_dict_type values(103, '薪酬项目类型', 'hr_salary_item_type', '0', 'admin', sysdate(), '', null, '薪酬项目类型');
insert into sys_dict_type values(104, '薪酬发放状态', 'hr_salary_record_status', '0', 'admin', sysdate(), '', null, '薪酬发放状态');

-- ----------------------------
-- 添加字典数据
-- ----------------------------
-- 员工职称
insert into sys_dict_data values(200, 1, '初级', '初级', 'hr_employee_title', '', 'primary', 'N', '0', 'admin', sysdate(), '', null, '初级职称');
insert into sys_dict_data values(201, 2, '中级', '中级', 'hr_employee_title', '', 'success', 'N', '0', 'admin', sysdate(), '', null, '中级职称');
insert into sys_dict_data values(202, 3, '高级', '高级', 'hr_employee_title', '', 'danger', 'N', '0', 'admin', sysdate(), '', null, '高级职称');

-- 员工学历
insert into sys_dict_data values(210, 1, '高中', '高中', 'hr_employee_education', '', '', 'N', '0', 'admin', sysdate(), '', null, '');
insert into sys_dict_data values(211, 2, '大专', '大专', 'hr_employee_education', '', '', 'N', '0', 'admin', sysdate(), '', null, '');
insert into sys_dict_data values(212, 3, '本科', '本科', 'hr_employee_education', '', '', 'N', '0', 'admin', sysdate(), '', null, '');
insert into sys_dict_data values(213, 4, '硕士', '硕士', 'hr_employee_education', '', '', 'N', '0', 'admin', sysdate(), '', null, '');
insert into sys_dict_data values(214, 5, '博士', '博士', 'hr_employee_education', '', '', 'N', '0', 'admin', sysdate(), '', null, '');

-- 员工状态
insert into sys_dict_data values(220, 1, '待复核', '0', 'hr_employee_status', '', 'warning', 'N', '0', 'admin', sysdate(), '', null, '待复核状态');
insert into sys_dict_data values(221, 2, '正常', '1', 'hr_employee_status', '', 'success', 'N', '0', 'admin', sysdate(), '', null, '正常状态');
insert into sys_dict_data values(222, 3, '已删除', '2', 'hr_employee_status', '', 'danger', 'N', '0', 'admin', sysdate(), '', null, '已删除状态');

-- 薪酬项目类型
insert into sys_dict_data values(230, 1, '收入', '0', 'hr_salary_item_type', '', 'success', 'N', '0', 'admin', sysdate(), '', null, '收入类型');
insert into sys_dict_data values(231, 2, '扣除', '1', 'hr_salary_item_type', '', 'danger', 'N', '0', 'admin', sysdate(), '', null, '扣除类型');

-- 薪酬发放状态
insert into sys_dict_data values(240, 1, '待复核', '0', 'hr_salary_record_status', '', 'warning', 'N', '0', 'admin', sysdate(), '', null, '待复核');
insert into sys_dict_data values(241, 2, '已复核', '1', 'hr_salary_record_status', '', 'success', 'N', '0', 'admin', sysdate(), '', null, '已复核');

-- ----------------------------
-- 添加菜单
-- ----------------------------
-- 人力资源管理一级菜单
insert into sys_menu values('2000', '人力资源管理', '0', '5', 'hr', null, '', '', 1, 0, 'M', '0', '0', '', 'peoples', 'admin', sysdate(), '', null, '人力资源管理目录');

-- 系统设置子菜单
insert into sys_menu values('2001', '机构管理', '2000', '1', 'organization', 'hr/organization/index', '', '', 1, 0, 'C', '0', '0', 'hr:organization:list', 'tree', 'admin', sysdate(), '', null, '机构管理菜单');
insert into sys_menu values('2002', '职位管理', '2000', '2', 'position', 'hr/position/index', '', '', 1, 0, 'C', '0', '0', 'hr:position:list', 'post', 'admin', sysdate(), '', null, '职位管理菜单');
insert into sys_menu values('2003', '薪酬项目', '2000', '3', 'salaryItem', 'hr/salaryItem/index', '', '', 1, 0, 'C', '0', '0', 'hr:salaryItem:list', 'money', 'admin', sysdate(), '', null, '薪酬项目菜单');

-- 人资档案子菜单
insert into sys_menu values('2010', '档案登记', '2000', '4', 'employeeRegister', 'hr/employee/register', '', '', 1, 0, 'C', '0', '0', 'hr:employee:add', 'edit', 'admin', sysdate(), '', null, '档案登记菜单');
insert into sys_menu values('2011', '档案复核', '2000', '5', 'employeeReview', 'hr/employee/review', '', '', 1, 0, 'C', '0', '0', 'hr:employee:review', 'form', 'admin', sysdate(), '', null, '档案复核菜单');
insert into sys_menu values('2012', '档案查询', '2000', '6', 'employeeQuery', 'hr/employee/index', '', '', 1, 0, 'C', '0', '0', 'hr:employee:list', 'search', 'admin', sysdate(), '', null, '档案查询菜单');

-- 薪酬管理子菜单
insert into sys_menu values('2020', '薪酬标准', '2000', '7', 'salaryStandard', 'hr/salaryStandard/index', '', '', 1, 0, 'C', '0', '0', 'hr:salaryStandard:list', 'money', 'admin', sysdate(), '', null, '薪酬标准菜单');
insert into sys_menu values('2021', '薪酬发放', '2000', '8', 'salaryRecord', 'hr/salaryRecord/index', '', '', 1, 0, 'C', '0', '0', 'hr:salaryRecord:list', 'money', 'admin', sysdate(), '', null, '薪酬发放菜单');

-- 按钮权限
-- 机构管理按钮
insert into sys_menu values('2100', '机构查询', '2001', '1', '', '', '', '', 1, 0, 'F', '0', '0', 'hr:organization:query', '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('2101', '机构新增', '2001', '2', '', '', '', '', 1, 0, 'F', '0', '0', 'hr:organization:add', '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('2102', '机构修改', '2001', '3', '', '', '', '', 1, 0, 'F', '0', '0', 'hr:organization:edit', '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('2103', '机构删除', '2001', '4', '', '', '', '', 1, 0, 'F', '0', '0', 'hr:organization:remove', '#', 'admin', sysdate(), '', null, '');

-- 职位管理按钮
insert into sys_menu values('2110', '职位查询', '2002', '1', '', '', '', '', 1, 0, 'F', '0', '0', 'hr:position:query', '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('2111', '职位新增', '2002', '2', '', '', '', '', 1, 0, 'F', '0', '0', 'hr:position:add', '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('2112', '职位修改', '2002', '3', '', '', '', '', 1, 0, 'F', '0', '0', 'hr:position:edit', '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('2113', '职位删除', '2002', '4', '', '', '', '', 1, 0, 'F', '0', '0', 'hr:position:remove', '#', 'admin', sysdate(), '', null, '');

-- 薪酬项目按钮
insert into sys_menu values('2120', '薪酬项目查询', '2003', '1', '', '', '', '', 1, 0, 'F', '0', '0', 'hr:salaryItem:query', '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('2121', '薪酬项目新增', '2003', '2', '', '', '', '', 1, 0, 'F', '0', '0', 'hr:salaryItem:add', '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('2122', '薪酬项目修改', '2003', '3', '', '', '', '', 1, 0, 'F', '0', '0', 'hr:salaryItem:edit', '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('2123', '薪酬项目删除', '2003', '4', '', '', '', '', 1, 0, 'F', '0', '0', 'hr:salaryItem:remove', '#', 'admin', sysdate(), '', null, '');

-- 员工档案按钮
insert into sys_menu values('2130', '档案查询', '2012', '1', '', '', '', '', 1, 0, 'F', '0', '0', 'hr:employee:query', '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('2131', '档案新增', '2012', '2', '', '', '', '', 1, 0, 'F', '0', '0', 'hr:employee:add', '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('2132', '档案修改', '2012', '3', '', '', '', '', 1, 0, 'F', '0', '0', 'hr:employee:edit', '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('2133', '档案删除', '2012', '4', '', '', '', '', 1, 0, 'F', '0', '0', 'hr:employee:remove', '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('2134', '档案复核', '2012', '5', '', '', '', '', 1, 0, 'F', '0', '0', 'hr:employee:review', '#', 'admin', sysdate(), '', null, '');

-- 薪酬标准按钮
insert into sys_menu values('2140', '薪酬标准查询', '2020', '1', '', '', '', '', 1, 0, 'F', '0', '0', 'hr:salaryStandard:query', '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('2141', '薪酬标准新增', '2020', '2', '', '', '', '', 1, 0, 'F', '0', '0', 'hr:salaryStandard:add', '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('2142', '薪酬标准修改', '2020', '3', '', '', '', '', 1, 0, 'F', '0', '0', 'hr:salaryStandard:edit', '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('2143', '薪酬标准删除', '2020', '4', '', '', '', '', 1, 0, 'F', '0', '0', 'hr:salaryStandard:remove', '#', 'admin', sysdate(), '', null, '');

-- 薪酬发放按钮
insert into sys_menu values('2150', '薪酬发放查询', '2021', '1', '', '', '', '', 1, 0, 'F', '0', '0', 'hr:salaryRecord:query', '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('2151', '薪酬发放新增', '2021', '2', '', '', '', '', 1, 0, 'F', '0', '0', 'hr:salaryRecord:add', '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('2152', '薪酬发放修改', '2021', '3', '', '', '', '', 1, 0, 'F', '0', '0', 'hr:salaryRecord:edit', '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('2153', '薪酬发放删除', '2021', '4', '', '', '', '', 1, 0, 'F', '0', '0', 'hr:salaryRecord:remove', '#', 'admin', sysdate(), '', null, '');
insert into sys_menu values('2154', '薪酬发放复核', '2021', '5', '', '', '', '', 1, 0, 'F', '0', '0', 'hr:salaryRecord:review', '#', 'admin', sysdate(), '', null, '');
