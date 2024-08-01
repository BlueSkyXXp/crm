import request from "@/utils/request";

// 查询客户列表
export function listCustomer(query) {
  return request({
    url: "/crm/customer/list?pageNum=" + query.pageNum + "&pageSize=" + query.pageSize,
    method: "post",
    data: query
  });
}

// 查询未处理客户列表
export function listUnprocessCustomer(query) {
  return request({
    url: "/crm/customer/list/unprocess?pageNum=" + query.pageNum + "&pageSize=" + query.pageSize,
    method: "post",
    data: query
  });
}

// 查询公共池客户列表
export function listPublicCustomer(query) {
  return request({
    url: "/crm/customer/list/public?pageNum=" + query.pageNum + "&pageSize=" + query.pageSize,
    method: "post",
    data: query
  });
}

// 查询团队客户列表
export function listGroupCustomer(query) {
  return request({
    url: "/crm/customer/list/team?pageNum=" + query.pageNum + "&pageSize=" + query.pageSize,
    method: "post",
    data: query
  });
}

// 查询离职人员客户列表
export function listResignCustomer(query) {
  return request({
    url: "/crm/customer/list/resign?pageNum=" + query.pageNum + "&pageSize=" + query.pageSize,
    method: "post",
    data: query
  });
}

// 查询客户详细
export function getCustomer(customerId) {
  return request({
    url: "/crm/customer/" + customerId,
    method: "get"
  });
}

// 新增客户
export function addCustomer(data) {
  return request({
    url: "/crm/customer",
    method: "post",
    data: data
  });
}

// 修改客户
export function updateCustomer(data) {
  return request({
    url: "/crm/customer",
    method: "put",
    data: data
  });
}

// 删除客户
export function delCustomer(customerId) {
  return request({
    url: "/crm/customer/" + customerId,
    method: "delete"
  });
}

// 分配客户
export function assignCustomer(data) {
  return request({
    url: "/crm/customer/assign",
    method: "put",
    data: data
  });
}

// 打入公共池
export function publicCustomer(data) {
  return request({
    url: "/crm/customer/public",
    method: "put",
    data: data
  });
}

// 待分配列表分配客户
export function assignNewCustomer(data) {
  return request({
    url: "/crm/new/assign",
    method: "put",
    data: data
  });
}

// 客户来源
export function optionCustomerSource() {
  return request({
    url: "/crm/customer/source",
    method: "get"
  });
}
