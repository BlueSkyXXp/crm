import request from "@/utils/request";

// 查询商机阶段变化列表
export function listChange(query) {
  return request({
    url: "/crm/change/list",
    method: "get",
    params: query
  });
}

// 查询商机阶段变化详细
export function getChange(changeId) {
  return request({
    url: "/crm/change/" + changeId,
    method: "get"
  });
}

// 新增商机阶段变化
export function addChange(data) {
  return request({
    url: "/crm/change",
    method: "post",
    data: data
  });
}

// 修改商机阶段变化
export function updateChange(data) {
  return request({
    url: "/crm/change",
    method: "put",
    data: data
  });
}

// 删除商机阶段变化
export function delChange(changeId) {
  return request({
    url: "/crm/change/" + changeId,
    method: "delete"
  });
}
