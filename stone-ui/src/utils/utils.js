import { listDept } from "@/api/crm/dept";
// 获取单个选项内的某个值
export const getOption = (o, v, k) => {
  const option = o.find(({ value }) => value === v);
  return option && option[k];
};
// todo
let res = { data: [] };
const getdept = async() => {
  res = await listDept();
};
getdept();
export const deptOption =
[
  { children: [{ id: "111", text: "一部" }], id: "110", text: "融翘科技" }
];

export const deptProps = {
  multiple: false,
  checkStrictly: true,
  label: "text",
  value: "id"
};
// export const deptProps = {
//   lazy: true,
//   emitPath: false,
//   async lazyLoad(node, resolve) {
//     const { level, value } = node
//     const data = await listDept()
//     const nodes = map(data, ({ id, text }) => ({
//       value: id,
//       label: text,
//       // leaf: level >= 2,
//     }))
//     resolve(nodes)
//   },
// }
export const hasOptions = [{
  label: "不限",
  value: undefined
}, {
  label: "无",
  value: 0
}, {
  label: "有",
  value: 1
}];
