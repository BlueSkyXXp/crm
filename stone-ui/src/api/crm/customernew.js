import request from "@/utils/request";

// 查询待分配客户列表
export function listCustomer(query) {
  return request({
    url: "/crm/new/list?pageNum=" + query.pageNum + "&pageSize=" + query.pageSize,
    method: "post",
    data: query
  });
}
// 查询客户详细
export function getCustomer(customerId) {
  return request({
    url: "/crm/new/" + customerId,
    method: "get"
  });
}

// 新增客户
export function addCustomer(data) {
  return request({
    url: "/crm/new",
    method: "post",
    data: data
  });
}

// 修改客户
export function updateCustomer(data) {
  return request({
    url: "/crm/new",
    method: "put",
    data: data
  });
}

// 删除客户
export function delCustomer(customerId) {
  return request({
    url: "/crm/new/" + customerId,
    method: "delete"
  });
}
