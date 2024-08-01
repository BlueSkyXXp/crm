import request from "@/utils/request";

// 查询客户跟踪列表
export function listCustomer_follow(query) {
  return request({
    url: "/crm/follow/list",
    method: "get",
    params: query
  });
}

// 查询客户跟踪详细
export function getCustomer_follow(id) {
  return request({
    url: "/crm/follow/" + id,
    method: "get"
  });
}

// 新增客户跟踪
export function addCustomer_follow(data) {
  return request({
    url: "/crm/follow",
    method: "post",
    data: data
  });
}

// 修改客户跟踪
export function updateCustomer_follow(data) {
  return request({
    url: "/crm/follow",
    method: "put",
    data: data
  });
}

// 删除客户跟踪
export function delCustomer_follow(id) {
  return request({
    url: "/crm/follow/" + id,
    method: "delete"
  });
}

// 查询客户跟踪详细
export function getFollowDetail(id) {
  return request({
    url: "/crm/follow/detail/" + id,
    method: "get"
  });
}
