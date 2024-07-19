import{d as $,D as I,i as g,R as T,r as c,$ as Y,o as m,c as z,b as l,w as s,a4 as y,h as _,e as i,u as o,a0 as O,j,a3 as J,a2 as h,t as v,P as p,aT as q,a1 as G,H as W,az as K,F as Q}from"./index-BbXpY4Iy.js";import{u as f}from"./useHandleData-io1dpySG.js";import{I as X,u as Z}from"./index-mKITjqvl.js";import{u as ee}from"./useAuthButtons-3Q0nVeg0.js";import{_ as ae}from"./index.vue_vue_type_script_setup_true_name_ProTable_lang-lY337Uvp.js";import{_ as te}from"./UserDrawer.vue_vue_type_script_setup_true_name_UserDrawer_lang-wPv5hSIs.js";import{a as le,d as C,r as re,e as x,B as se,h as ie,i as ne,b as oe,c as ue,j as ce}from"./user-AHJD5viY.js";import"./_plugin-vue_export-helper-x3n3nnut.js";import"./sortable.esm-7KB62mPT.js";import"./Imgs-l7sbwhV1.js";import"./upload-LPSH1f05.js";const de={class:"table-box"},pe=$({name:"useProTable"}),$e=$({...pe,setup(me){const S=I(),D=()=>{S.push(`/proTable/useProTable/detail/${Math.random().toFixed(3)}?params=detail-page`)},n=g(),L=T({type:1}),U=e=>({list:e.list,total:e.total,pageNum:e.pageNum,pageSize:e.pageSize}),A=e=>{let a=JSON.parse(JSON.stringify(e));return a.createTime&&(a.startTime=a.createTime[0]),a.createTime&&(a.endTime=a.createTime[1]),delete a.createTime,le(a)},{BUTTONS:N}=ee(),V=T([{type:"selection",fixed:"left",width:70},{type:"sort",label:"Sort",width:80},{type:"expand",label:"Expand",width:85},{prop:"username",label:"用户姓名",search:{el:"input",tooltip:"我是搜索提示"},render:e=>l(c("el-button"),{type:"primary",link:!0,onClick:()=>p.success("我是通过 tsx 语法渲染的内容")},{default:()=>[e.row.username]})},{prop:"gender",label:"性别",enum:oe,search:{el:"select",props:{filterable:!0}},fieldNames:{label:"genderLabel",value:"genderValue"}},{prop:"user.detail.age",label:"年龄",search:{render:({searchParam:e})=>l("div",{class:"flx-center"},[l(c("el-input"),{modelValue:e.minAge,modelModifiers:{trim:!0},"onUpdate:modelValue":a=>e.minAge=a,placeholder:"最小年龄"},null),l("span",{class:"mr10 ml10"},[i("-")]),l(c("el-input"),{modelValue:e.maxAge,modelModifiers:{trim:!0},"onUpdate:modelValue":a=>e.maxAge=a,placeholder:"最大年龄"},null)])}},{prop:"idCard",label:"身份证号",search:{el:"input"}},{prop:"email",label:"邮箱"},{prop:"address",label:"居住地址"},{prop:"status",label:"用户状态",enum:ue,search:{el:"tree-select",props:{filterable:!0}},fieldNames:{label:"userLabel",value:"userStatus"},render:e=>l(Q,null,[N.value.status?l(c("el-switch"),{"model-value":e.row.status,"active-text":e.row.status?"启用":"禁用","active-value":1,"inactive-value":0,onClick:()=>M(e.row)},null):l(c("el-tag"),{type:e.row.status?"success":"danger"},{default:()=>[e.row.status?"启用":"禁用"]})])},{prop:"createTime",label:"创建时间",headerRender:e=>l(c("el-button"),{type:"primary",onClick:()=>p.success("我是通过 tsx 语法渲染的表头")},{default:()=>[e.column.label]}),width:180,search:{el:"date-picker",span:2,props:{type:"datetimerange",valueFormat:"YYYY-MM-DD HH:mm:ss"},defaultValue:["2022-11-12 11:35:00","2022-12-12 11:35:00"]}},{prop:"operation",label:"操作",fixed:"right",width:330}]),B=({newIndex:e,oldIndex:a})=>{var t;(t=n.value)==null||t.tableData,p.success("修改列表排序成功")},F=async e=>{var a;await f(C,{id:[e.id]},`删除【${e.username}】用户`),(a=n.value)==null||a.getTableList()},R=async e=>{var a,t;await f(C,{id:e},"删除所选用户信息"),(a=n.value)==null||a.clearSelection(),(t=n.value)==null||t.getTableList()},E=async e=>{var a;await f(re,{id:e.id},`重置【${e.username}】用户密码`),(a=n.value)==null||a.getTableList()},M=async e=>{var a;await f(ce,{id:e.id,status:e.status==1?0:1},`切换【${e.username}】用户状态`),(a=n.value)==null||a.getTableList()},P=async()=>{K.confirm("确认导出用户数据?","温馨提示",{type:"warning"}).then(()=>{var e;return Z(x,"用户列表",(e=n.value)==null?void 0:e.searchParam)})},k=g(null),H=()=>{var a,t;const e={title:"用户",tempApi:x,importApi:se,getTableList:(a=n.value)==null?void 0:a.getTableList};(t=k.value)==null||t.acceptParams(e)},w=g(null),b=(e,a={})=>{var d,r;const t={title:e,isView:e==="查看",row:{...a},api:e==="新增"?ie:e==="编辑"?ne:void 0,getTableList:(d=n.value)==null?void 0:d.getTableList};(r=w.value)==null||r.acceptParams(t)};return(e,a)=>{const t=c("el-button"),d=Y("auth");return m(),z("div",de,[l(ae,{ref_key:"proTable",ref:n,columns:V,"request-api":A,"init-param":L,"data-callback":U,onDargSort:B},{tableHeader:s(r=>[y((m(),_(t,{type:"primary",icon:o(O),onClick:a[0]||(a[0]=u=>b("新增"))},{default:s(()=>[i("新增用户")]),_:1},8,["icon"])),[[d,"add"]]),y((m(),_(t,{type:"primary",icon:o(j),plain:"",onClick:H},{default:s(()=>[i("批量添加用户")]),_:1},8,["icon"])),[[d,"batchAdd"]]),y((m(),_(t,{type:"primary",icon:o(J),plain:"",onClick:P},{default:s(()=>[i("导出用户数据")]),_:1},8,["icon"])),[[d,"export"]]),l(t,{type:"primary",plain:"",onClick:D},{default:s(()=>[i("To 子集详情页面")]),_:1}),l(t,{type:"danger",icon:o(h),plain:"",disabled:!r.isSelected,onClick:u=>R(r.selectedListIds)},{default:s(()=>[i(" 批量删除用户 ")]),_:2},1032,["icon","disabled","onClick"])]),expand:s(r=>[i(v(r.row),1)]),usernameHeader:s(r=>[l(t,{type:"primary",onClick:a[1]||(a[1]=u=>o(p).success("我是通过作用域插槽渲染的表头"))},{default:s(()=>[i(v(r.column.label),1)]),_:2},1024)]),createTime:s(r=>[l(t,{type:"primary",link:"",onClick:a[2]||(a[2]=u=>o(p).success("我是通过作用域插槽渲染的内容"))},{default:s(()=>[i(v(r.row.createTime),1)]),_:2},1024)]),operation:s(r=>[l(t,{type:"primary",link:"",icon:o(q),onClick:u=>b("查看",r.row)},{default:s(()=>[i("查看")]),_:2},1032,["icon","onClick"]),l(t,{type:"primary",link:"",icon:o(G),onClick:u=>b("编辑",r.row)},{default:s(()=>[i("编辑")]),_:2},1032,["icon","onClick"]),l(t,{type:"primary",link:"",icon:o(W),onClick:u=>E(r.row)},{default:s(()=>[i("重置密码")]),_:2},1032,["icon","onClick"]),l(t,{type:"primary",link:"",icon:o(h),onClick:u=>F(r.row)},{default:s(()=>[i("删除")]),_:2},1032,["icon","onClick"])]),_:1},8,["columns","init-param"]),l(te,{ref_key:"drawerRef",ref:w},null,512),l(X,{ref_key:"dialogRef",ref:k},null,512)])}}});export{$e as default};
