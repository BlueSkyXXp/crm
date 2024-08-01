import request from "@/utils/request";

// 查询单变更日志记录列表
export function listLog(query) {
  return request({
    url: "/system/formLog/list",
    method: "get",
    params: query
  });
}

// 查询单变更日志记录详细
export function getLog(customerId) {
  return request({
    url: "/system/formLog/" + customerId,
    method: "get"
  });
}

// 新增单变更日志记录
export function addLog(data) {
  return request({
    url: "/system/formLog",
    method: "post",
    data: data
  });
}

// 修改单变更日志记录
export function updateLog(data) {
  return request({
    url: "/system/formLog",
    method: "put",
    data: data
  });
}

// 删除单变更日志记录
export function delLog(customerId) {
  return request({
    url: "/system/formLog/" + customerId,
    method: "delete"
  });
}
