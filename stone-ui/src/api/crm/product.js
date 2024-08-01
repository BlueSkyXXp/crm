import request from "@/utils/request";

// 查询合同产品关系列表
export function listProduct(query) {
  return request({
    url: "/crm/product/list",
    method: "get",
    params: query
  });
}

// 查询合同产品关系详细
export function getProduct(rId) {
  return request({
    url: "/crm/product/" + rId,
    method: "get"
  });
}

// 新增合同产品关系
export function addProduct(data) {
  return request({
    url: "/crm/product",
    method: "post",
    data: data
  });
}

// 修改合同产品关系
export function updateProduct(data) {
  return request({
    url: "/crm/product",
    method: "put",
    data: data
  });
}

// 删除合同产品关系
export function delProduct(rId) {
  return request({
    url: "/crm/product/" + rId,
    method: "delete"
  });
}
