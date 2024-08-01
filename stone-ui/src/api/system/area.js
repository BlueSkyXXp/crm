import request from "@/utils/request";

// 查询中国标准行政区列表
export function listArea(query) {
  return request({
    url: "/system/area/list",
    method: "get",
    params: query
  });
}

// 查询中国标准行政区详细
export function getArea(id) {
  return request({
    url: "/system/area/" + id,
    method: "get"
  });
}

// 新增中国标准行政区
export function addArea(data) {
  return request({
    url: "/system/area",
    method: "post",
    data: data
  });
}

// 修改中国标准行政区
export function updateArea(data) {
  return request({
    url: "/system/area",
    method: "put",
    data: data
  });
}

// 删除中国标准行政区
export function delArea(id) {
  return request({
    url: "/system/area/" + id,
    method: "delete"
  });
}
