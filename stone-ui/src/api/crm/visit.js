import request from "@/utils/request";

// 查询客户来访列表
export function listVisit(query) {
  return request({
    url: "/crm/visit/result?pageNum=" + query.pageNum + "&pageSize=" + query.pageSize,
    method: "post",
    data: query
  });
}


export function listVisitDetail(query) {
  return request({
    url: "/crm/visit/list?pageNum=" + query.pageNum + "&pageSize=" + query.pageSize,
    method: "post",
    data: query
  });
}

// 查询客户来访详细
export function getVisit(id) {
  return request({
    url: "/crm/visit/" + id,
    method: "get"
  });
}

// 新增客户来访
export function addVisit(data) {
  return request({
    url: "/crm/visit",
    method: "post",
    data: data
  });
}

// 修改客户来访
export function updateVisit(data) {
  return request({
    url: "/crm/visit",
    method: "put",
    data: data
  });
}

// 删除客户来访
export function delVisit(id) {
  return request({
    url: "/crm/visit/" + id,
    method: "delete"
  });
}

// 新增客户来访-扫码签到
export function addVisitByQRCode(data) {
  return request({
    url: "/qiandao/jLiljpZlRD8yqqpuiedJZVE1ltyXuUjf",
    method: "post",
    data: data
  });
}
