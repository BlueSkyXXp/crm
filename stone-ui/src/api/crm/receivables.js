import request from "@/utils/request";

// 查询回款列表
export function listReceivables(query) {
  return request({
    url: "/crm/receivables/list",
    method: "get",
    params: query
  });
}

// 查询回款详细
export function getReceivables(receivablesId) {
  return request({
    url: "/crm/receivables/" + receivablesId,
    method: "get"
  });
}

// 新增回款
export function addReceivables(data) {
  return request({
    url: "/crm/receivables",
    method: "post",
    data: data
  });
}

// 修改回款
export function updateReceivables(data) {
  return request({
    url: "/crm/receivables",
    method: "put",
    data: data
  });
}

// 删除回款
export function delReceivables(receivablesId) {
  return request({
    url: "/crm/receivables/" + receivablesId,
    method: "delete"
  });
}
