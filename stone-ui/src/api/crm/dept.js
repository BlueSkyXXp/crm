import request from "@/utils/request";

// 查询用户部门
export function listDept(query) {
  return request({
    url: "system/userdept/deptlist",
    method: "post",
    data: query || {}
  });
}

// 查询部门业务员
export function deptUser(deptId) {
  return request({
    url: "/system/userdept/list/" + deptId,
    method: "get"
  });
}

// 查询用户信息
export function deptUserInfo(userId) {
  return request({
    url: "/system/userdept/user/" + userId,
    method: "get"
  });
}
