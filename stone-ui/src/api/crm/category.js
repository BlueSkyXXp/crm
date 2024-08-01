import request from "@/utils/request";

// 查询产品分类列表
export function listCategory(query) {
  return request({
    url: "/crm/category/list",
    method: "get",
    params: query
  });
}

// 查询产品分类全部列表
export function allListCategory(query) {
  return request({
    url: "/crm/category/allList",
    method: "get",
    params: query
  });
}

// 查询产品分类详细
export function getCategory(categoryId) {
  return request({
    url: "/crm/category/" + categoryId,
    method: "get"
  });
}

// 新增产品分类
export function addCategory(data) {
  return request({
    url: "/crm/category",
    method: "post",
    data: data
  });
}

// 修改产品分类
export function updateCategory(data) {
  return request({
    url: "/crm/category",
    method: "put",
    data: data
  });
}

// 删除产品分类
export function delCategory(categoryId) {
  return request({
    url: "/crm/category/" + categoryId,
    method: "delete"
  });
}
