import request from "@/utils/request";

// 查询负责人变更记录列表
export function listRecord(query) {
  return request({
    url: "/crm/record/list",
    method: "get",
    params: query
  });
}

// 查询负责人变更记录详细
export function getRecord(recordId) {
  return request({
    url: "/crm/record/" + recordId,
    method: "get"
  });
}

// 新增负责人变更记录
export function addRecord(data) {
  return request({
    url: "/crm/record",
    method: "post",
    data: data
  });
}

// 修改负责人变更记录
export function updateRecord(data) {
  return request({
    url: "/crm/record",
    method: "put",
    data: data
  });
}

// 删除负责人变更记录
export function delRecord(recordId) {
  return request({
    url: "/crm/record/" + recordId,
    method: "delete"
  });
}
