import request from "@/utils/request";

// 查询合同列表
export function listContract(query) {
  return request({
    url: "/crm/contract/list",
    method: "get",
    params: query
  });
}

export function listContractProducts(query) {
  return request({
    url: "crm/contract/product/contractProductList",
    method: "get",
    params: query
  });
}

// 查询合同详细
export function getContract(contractId) {
  return request({
    url: "/crm/contract/" + contractId,
    method: "get"
  });
}

// 新增合同
export function addContract(data) {
  return request({
    url: "/crm/contract",
    method: "post",
    data: data
  });
}

// 修改合同
export function updateContract(data) {
  return request({
    url: "/crm/contract",
    method: "put",
    data: data
  });
}

// 删除合同
export function delContract(contractId) {
  return request({
    url: "/crm/contract/" + contractId,
    method: "delete"
  });
}
