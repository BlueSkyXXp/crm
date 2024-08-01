import request from "@/utils/request";

// 查询审件列表
export function listInfo(query) {
  return request({
    url: "/crm/approval_info/list" + "?pageNum=" + query.pageNum + "&pageSize=" + query.pageSize,
    method: "post",
    data: query
  });
}

// 查询业绩排行榜
export function result(query) {
  return request({
    url: "/crm/approval_info/result?pageNum=" + query.pageNum + "&pageSize=" + query.pageSize,
    method: "post",
    data: query
  });
}

// 查询审件详细
export function getInfo(id) {
  return request({
    url: "/crm/approval_info/" + id,
    method: "get"
  });
}

// 新增审件
export function addInfo(data) {
  return request({
    url: "/crm/approval_info",
    method: "post",
    data: data
  });
}

// 修改审件
export function updateInfo(data) {
  return request({
    url: "/crm/approval_info",
    method: "put",
    data: data
  });
}

// 审核审件
export function auditInfo(data) {
  return request({
    url: "/crm/approval_info/audit",
    method: "post",
    data: data
  });
}
// 删除审件
export function delInfo(id) {
  return request({
    url: "/crm/approval_info/" + id,
    method: "delete"
  });
}
