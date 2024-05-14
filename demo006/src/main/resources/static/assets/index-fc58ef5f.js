import{r as i,A as j,_ as c,bX as f,bY as _,j as e,bt as B,B as H,y as z,I as N,bV as $,p as w,q as y,bZ as I,b_ as V,b$ as M,c0 as A,c1 as L,c2 as R,b3 as T,c3 as F}from"./index-e632bbdf.js";import{I as E,H as q,F as D,C as K,S as X,D as Z,M as G}from"./dropdown-667953aa.js";import{T as J}from"./index-54097704.js";var P={icon:{tag:"svg",attrs:{viewBox:"64 64 896 896",focusable:"false"},children:[{tag:"path",attrs:{d:"M816 768h-24V428c0-141.1-104.3-257.7-240-277.1V112c0-22.1-17.9-40-40-40s-40 17.9-40 40v38.9c-135.7 19.4-240 136-240 277.1v340h-24c-17.7 0-32 14.3-32 32v32c0 4.4 3.6 8 8 8h216c0 61.8 50.2 112 112 112s112-50.2 112-112h216c4.4 0 8-3.6 8-8v-32c0-17.7-14.3-32-32-32zM512 888c-26.5 0-48-21.5-48-48h96c0 26.5-21.5 48-48 48zM304 768V428c0-55.6 21.6-107.8 60.9-147.1S456.4 220 512 220c55.6 0 107.8 21.6 147.1 60.9S720 372.4 720 428v340H304z"}}]},name:"bell",theme:"outlined"};const U=P;var v=function(s,t){return i.createElement(j,c(c({},s),{},{ref:t,icon:U}))};v.displayName="BellOutlined";const W=i.forwardRef(v);var Y={icon:{tag:"svg",attrs:{viewBox:"64 64 896 896",focusable:"false"},children:[{tag:"path",attrs:{d:"M870 126H663.8c-17.4 0-32.9 11.9-37 29.3C614.3 208.1 567 246 512 246s-102.3-37.9-114.8-90.7a37.93 37.93 0 00-37-29.3H154a44 44 0 00-44 44v252a44 44 0 0044 44h75v388a44 44 0 0044 44h478a44 44 0 0044-44V466h75a44 44 0 0044-44V170a44 44 0 00-44-44zm-28 268H723v432H301V394H182V198h153.3c28.2 71.2 97.5 120 176.7 120s148.5-48.8 176.7-120H842v196z"}}]},name:"skin",theme:"outlined"};const Q=Y;var k=function(s,t){return i.createElement(j,c(c({},s),{},{ref:t,icon:Q}))};k.displayName="SkinOutlined";const ee=i.forwardRef(k),r=E;r.Header=q;r.Footer=D;r.Content=K;r.Sider=X;const x=r;function te(l,s){let t=null;return function(...o){t!==null&&clearTimeout(t),t=setTimeout(()=>{l.apply(this,o),t=null},s)}}const ne="_skin_jyxqh_1",se="_skin_input_jyxqh_9",g={skin:ne,skin_input:se},ae=()=>{const{setUserInfo:l}=f(),{setColor:s,primaryColor:t}=_(),o=()=>{l(null)},d=[{key:"1",label:e.jsx("span",{onClick:o,children:"退出登录"})},{key:"2",label:"个人中心"}],u=h=>{s(h.target.value)};return e.jsxs(B,{size:20,children:[e.jsx("span",{style:{display:"flex"},children:e.jsx(H,{count:12,children:e.jsx(W,{style:{fontSize:24}})})}),e.jsxs("div",{className:g.skin,children:[e.jsx(z,{type:"primary",shape:"circle",icon:e.jsx(ee,{})}),e.jsx(N,{type:"color",className:g.skin_input,defaultValue:t,onChange:te(u,500)})]}),e.jsx(Z,{menu:{items:d},placement:"bottomRight",children:e.jsx($,{src:"https://gw.alipayobjects.com/zos/antfincdn/XAosXuNZyF/BiazfanxmamNRoxxVxka.png",style:{cursor:"pointer"}})})]})},{Text:le}=J,oe=()=>e.jsxs(w,{justify:"space-between",align:"middle",children:[e.jsx(y,{children:e.jsx(le,{strong:!0,style:{fontSize:18},children:"react-template-admin"})}),e.jsx(y,{style:{display:"flex"},children:e.jsx(ae,{})})]}),ie=()=>e.jsx("div",{style:{display:"flex",height:"100%",alignItems:"center",justifyContent:"center"},children:"暂无权限......"}),{Header:re,Content:ce,Footer:de,Sider:ue}=x,me=()=>{const[l,s]=i.useState(!1),t=I(),{pathname:o}=V(),{userInfo:d}=f(),{token:{colorBgContainer:u}}=M.useToken(),{isAdmin:h}=A(),m=n=>n.map(a=>{var p;return{key:a.index?"/":(p=a.path)!=null&&p.startsWith("/")?a.path:`/${a.path}`,icon:a.icon,label:a.title,children:a.children?m(a.children):null}}),b=m(L[0].children[0].children.filter(n=>n.path!=="*")),S=({key:n})=>{t(n)};if(!d)return e.jsx(R,{to:"/login",replace:!0});const C=()=>{const n=o.split("/").slice(0,-1);return n.map((p,O)=>"/"+n.slice(1,O+1).join("/"))};return e.jsxs(x,{style:{minHeight:"100vh"},children:[e.jsxs(ue,{style:{overflow:"auto",height:"100vh"},collapsible:!0,collapsed:l,onCollapse:n=>s(n),children:[e.jsx("div",{style:{height:32,margin:16,background:"rgba(255, 255, 255, 0.2)"}}),e.jsx(G,{theme:"dark",defaultSelectedKeys:[o],defaultOpenKeys:C(),mode:"inline",items:b,onClick:S})]}),e.jsxs(x,{className:"site-layout",children:[e.jsx(re,{style:{padding:"0 10px",background:u},children:e.jsx(oe,{})}),e.jsx(ce,{style:{padding:16,overflow:"auto",height:"calc(100vh - 128px)"},children:h?e.jsx(i.Suspense,{fallback:e.jsx(T,{size:"large",className:"content_spin"}),children:e.jsx(F,{})}):e.jsx(ie,{})}),e.jsx(de,{style:{textAlign:"center"},children:"react template admin ©2023 Created by Jade"})]})]})};export{me as default};