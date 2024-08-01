import request from "@/utils/request";

// 查询回款计划列表
export function listPlan(query) {
  return request({
    url: "/crm/plan/list",
    method: "get",
    params: query
  });
}

// 查询回款计划详细
export function getPlan(planId) {
  return request({
    url: "/crm/plan/" + planId,
    method: "get"
  });
}

// 新增回款计划
export function addPlan(data) {
  return request({
    url: "/crm/plan",
    method: "post",
    data: data
  });
}

// 修改回款计划
export function updatePlan(data) {
  return request({
    url: "/crm/plan",
    method: "put",
    data: data
  });
}

// 删除回款计划
export function delPlan(planId) {
  return request({
    url: "/crm/plan/" + planId,
    method: "delete"
  });
}
