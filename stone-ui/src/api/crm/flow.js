import request from "@/utils/request";

// 查询客户跟踪备注列表
export function listCustomer_flow(query) {
  return request({
    url: "/crm/customer_flow/list",
    method: "get",
    params: query
  });
}

