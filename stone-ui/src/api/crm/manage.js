import request from "@/utils/request";

// 查询团队管理列表
export function listManage(query) {
  return request({
    url: "/crm/team_manage/list",
    method: "get",
    params: query
  });
}

// 日内数据统计
export function statistics(query) {
  return request({
    url: "/crm/team_manage/statistics?pageNum=" + query.pageNum + "&pageSize=" + query.pageSize,
    method: "post",
    data: query
  });
}

// 一键接收开启关闭
export function allowReceive(data) {
  return request({
    url: "/crm/team_manage/allow_receive",
    method: "put",
    data: data
  });
}

// 查询团队管理详细
export function getManage(id) {
  return request({
    url: "/crm/team_manage/" + id,
    method: "get"
  });
}

// 新增团队管理
export function addManage(data) {
  return request({
    url: "/crm/team_manage",
    method: "post",
    data: data
  });
}

// 修改团队管理
export function updateManage(data) {
  return request({
    url: "/crm/team_manage",
    method: "put",
    data: data
  });
}

// 删除团队管理
export function delManage(id) {
  return request({
    url: "/crm/team_manage/" + id,
    method: "delete"
  });
}
