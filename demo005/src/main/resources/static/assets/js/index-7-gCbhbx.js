import{g as n}from"./user-AHJD5viY.js";import{T as i}from"./index-0Bk1KCjD.js";import{d as f,R as c,r as p,o as g,c as x,b as e,u,a as m,w as t,e as l,P as _,p as v,g as F}from"./index-BbXpY4Iy.js";import{_ as V}from"./_plugin-vue_export-helper-x3n3nnut.js";const q=o=>(v("data-v-f2afdcf9"),o=o(),F(),o),C={class:"content-box"},T={class:"descriptions-box card"},N=q(()=>m("span",{class:"text"}," 树形筛选器 🍓🍇🍈🍉",-1)),S=f({name:"treeFilter"}),w=f({...S,setup(o){const d=c({departmentId:"1"}),b=s=>{_.success(`你选择了 id 为 ${s} 的数据🤔`),d.departmentId=s},r=c({departmentId:["11"]}),h=s=>{_.success(`你选择了 id 为 ${JSON.stringify(s)} 的数据🤔`),r.departmentId=s};return(s,B)=>{const a=p("el-descriptions-item"),I=p("el-descriptions");return g(),x("div",C,[e(i,{label:"name",title:"部门列表(单选)","request-api":u(n),"default-value":d.departmentId,onChange:b},null,8,["request-api","default-value"]),e(i,{title:"部门列表(多选)",multiple:"",label:"name","request-api":u(n),"default-value":r.departmentId,onChange:h},null,8,["request-api","default-value"]),m("div",T,[N,e(I,{title:"配置项 📚",column:1,border:""},{default:t(()=>[e(a,{label:"requestApi"},{default:t(()=>[l(" 请求分类数据的 api ")]),_:1}),e(a,{label:"data"},{default:t(()=>[l(" 分类数据，如果有分类数据，则不会执行 api 请求 ")]),_:1}),e(a,{label:"title"},{default:t(()=>[l(" treeFilter 标题 ")]),_:1}),e(a,{label:"id"},{default:t(()=>[l(" 选择的id，默认为 “id” ")]),_:1}),e(a,{label:"label"},{default:t(()=>[l(" 显示的label，默认为 “label” ")]),_:1}),e(a,{label:"multiple"},{default:t(()=>[l(" 是否为多选，默认为 false ")]),_:1}),e(a,{label:"defaultValue"},{default:t(()=>[l(" 默认选中的值 ")]),_:1})]),_:1})])])}}}),A=V(w,[["__scopeId","data-v-f2afdcf9"]]);export{A as default};
