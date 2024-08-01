import request from "@/utils/request";

// 查询联系人列表
export function listContacts(query) {
  return request({
    url: "/crm/contacts/list",
    method: "get",
    params: query
  });
}

// 查询联系人详细
export function getContacts(contactsId) {
  return request({
    url: "/crm/contacts/" + contactsId,
    method: "get"
  });
}

// 新增联系人
export function addContacts(data) {
  return request({
    url: "/crm/contacts",
    method: "post",
    data: data
  });
}

// 修改联系人
export function updateContacts(data) {
  return request({
    url: "/crm/contacts",
    method: "put",
    data: data
  });
}

// 删除联系人
export function delContacts(contactsId) {
  return request({
    url: "/crm/contacts/" + contactsId,
    method: "delete"
  });
}
