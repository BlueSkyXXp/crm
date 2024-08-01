import request from "@/utils/request";

// 查询线索列表
export function listLeads(query) {
  return request({
    url: "/crm/leads/list",
    method: "get",
    params: query
  });
}

// 查询线索详细
export function getLeads(leadsId) {
  return request({
    url: "/crm/leads/" + leadsId,
    method: "get"
  });
}

// 新增线索
export function addLeads(data) {
  return request({
    url: "/crm/leads",
    method: "post",
    data: data
  });
}

// 修改线索
export function updateLeads(data) {
  return request({
    url: "/crm/leads",
    method: "put",
    data: data
  });
}

// 删除线索
export function delLeads(leadsId) {
  return request({
    url: "/crm/leads/" + leadsId,
    method: "delete"
  });
}
