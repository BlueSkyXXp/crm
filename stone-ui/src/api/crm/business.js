import request from "@/utils/request";

// 查询商机联系人关联列表
export function listBusiness(query) {
  return request({
    url: "/crm/business/list",
    method: "get",
    params: query
  });
}

// 查询商机联系人关联详细
export function getBusiness(id) {
  return request({
    url: "/crm/business/" + id,
    method: "get"
  });
}

// 新增商机联系人关联
export function addBusiness(data) {
  return request({
    url: "/crm/business",
    method: "post",
    data: data
  });
}

// 修改商机联系人关联
export function updateBusiness(data) {
  return request({
    url: "/crm/business",
    method: "put",
    data: data
  });
}

// 删除商机联系人关联
export function delBusiness(id) {
  return request({
    url: "/crm/business/" + id,
    method: "delete"
  });
}
