import request from "@/utils/request";

// 查询日内部数据统计列表
export function listStatic(query) {
  return request({
    url: "/crm/static/list",
    method: "get",
    params: query
  });
}

// 查询日内部数据统计详细
export function getStatic(deptName) {
  return request({
    url: "/crm/static/" + deptName,
    method: "get"
  });
}

// 新增日内部数据统计
export function addStatic(data) {
  return request({
    url: "/crm/static",
    method: "post",
    data: data
  });
}

// 修改日内部数据统计
export function updateStatic(data) {
  return request({
    url: "/crm/static",
    method: "put",
    data: data
  });
}

// 删除日内部数据统计
export function delStatic(deptName) {
  return request({
    url: "/crm/static/" + deptName,
    method: "delete"
  });
}
