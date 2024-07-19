import{d as z,i as w,ak as W,G as S,x as q,r as u,o as f,c as h,b as a,w as p,F as X,a as i,Y as U,v as F,ad as C,q as M,h as N,ac as _,p as P,g as H,al as K,Z as G,_ as Y,U as x,am as ee,u as le}from"./index-BbXpY4Iy.js";import{u as A}from"./upload-LPSH1f05.js";import{_ as J}from"./_plugin-vue_export-helper-x3n3nnut.js";const Z=c=>(P("data-v-6d73ecbd"),c=c(),H(),c),te={class:"upload-box"},ae=["src"],se=Z(()=>i("span",null,"编辑",-1)),oe=Z(()=>i("span",null,"查看",-1)),ie=Z(()=>i("span",null,"删除",-1)),ne={key:1,class:"upload-empty"},de={class:"el-upload__tip"},ue=z({name:"UploadImg"}),ce=z({...ue,props:{imageUrl:{default:""},api:{},drag:{type:Boolean,default:!0},disabled:{type:Boolean,default:!1},fileSize:{default:5},fileType:{default:()=>["image/jpeg","image/png","image/gif"]},height:{default:"150px"},width:{default:"150px"},borderRadius:{default:"8px"}},emits:["update:imageUrl"],setup(c,{emit:E}){K(l=>({"21cec5b4":l.width,"4ba7e472":l.height,"7492ff04":l.borderRadius}));const s=c,m=w("id-"+W()),g=w(!1),r=S(G,void 0),n=S(Y,void 0),y=q(()=>s.disabled||(r==null?void 0:r.disabled)),$=E,k=async l=>{let o=new FormData;o.append("file",l.file);try{const e=s.api??A,{data:t}=await e(o);$("update:imageUrl",t.fileUrl),n!=null&&n.prop&&(r==null||r.validateField([n.prop]))}catch(e){l.onError(e)}},T=()=>{$("update:imageUrl","")},D=()=>{const l=document.querySelector(`#${m.value} .el-upload__input`);l&&l.dispatchEvent(new MouseEvent("click"))},B=l=>{const o=l.size/1024/1024<s.fileSize,e=s.fileType.includes(l.type);return e||_({title:"温馨提示",message:"上传图片不符合所需的格式！",type:"warning"}),o||setTimeout(()=>{_({title:"温馨提示",message:`上传图片大小不能超过 ${s.fileSize}M！`,type:"warning"})},0),e&&o},L=()=>{_({title:"温馨提示",message:"图片上传成功！",type:"success"})},I=()=>{_({title:"温馨提示",message:"图片上传失败，请您重新上传！",type:"error"})};return(l,o)=>{const e=u("Edit"),t=u("el-icon"),d=u("ZoomIn"),b=u("Delete"),V=u("Plus"),j=u("el-upload"),R=u("el-image-viewer");return f(),h("div",te,[a(j,{id:m.value,action:"#",class:M(["upload",y.value?"disabled":"",l.drag?"no-border":""]),multiple:!1,disabled:y.value,"show-file-list":!1,"http-request":k,"before-upload":B,"on-success":L,"on-error":I,drag:l.drag,accept:l.fileType.join(",")},{default:p(()=>[l.imageUrl?(f(),h(X,{key:0},[i("img",{src:l.imageUrl,class:"upload-image"},null,8,ae),i("div",{class:"upload-handle",onClick:o[1]||(o[1]=F(()=>{},["stop"]))},[y.value?U("",!0):(f(),h("div",{key:0,class:"handle-icon",onClick:D},[a(t,null,{default:p(()=>[a(e)]),_:1}),se])),i("div",{class:"handle-icon",onClick:o[0]||(o[0]=v=>g.value=!0)},[a(t,null,{default:p(()=>[a(d)]),_:1}),oe]),y.value?U("",!0):(f(),h("div",{key:1,class:"handle-icon",onClick:T},[a(t,null,{default:p(()=>[a(b)]),_:1}),ie]))])],64)):(f(),h("div",ne,[C(l.$slots,"empty",{},()=>[a(t,null,{default:p(()=>[a(V)]),_:1})],!0)]))]),_:3},8,["id","class","disabled","drag","accept"]),i("div",de,[C(l.$slots,"tip",{},void 0,!0)]),g.value?(f(),N(R,{key:0,"url-list":[l.imageUrl],onClose:o[2]||(o[2]=v=>g.value=!1)},null,8,["url-list"])):U("",!0)])}}}),ke=J(ce,[["__scopeId","data-v-6d73ecbd"]]),O=c=>(P("data-v-5c930775"),c=c(),H(),c),re={class:"upload-box"},pe={class:"upload-empty"},me=["src"],fe=["onClick"],_e=O(()=>i("span",null,"查看",-1)),ge=["onClick"],ve=O(()=>i("span",null,"删除",-1)),he={class:"el-upload__tip"},ye=z({name:"UploadImgs"}),be=z({...ye,props:{fileList:{default:()=>[]},api:{},drag:{type:Boolean,default:!0},disabled:{type:Boolean,default:!1},limit:{default:5},fileSize:{default:5},fileType:{default:()=>["image/jpeg","image/png","image/gif"]},height:{default:"150px"},width:{default:"150px"},borderRadius:{default:"8px"}},emits:["update:fileList"],setup(c,{emit:E}){K(e=>({"8b546a3e":e.borderRadius,"62eeec23":e.width,"3dc0c32c":e.height}));const s=c,m=S(G,void 0),g=S(Y,void 0),r=q(()=>s.disabled||(m==null?void 0:m.disabled)),n=w(s.fileList);x(()=>s.fileList,e=>{n.value=e});const y=e=>{const t=e.size/1024/1024<s.fileSize,d=s.fileType.includes(e.type);return d||_({title:"温馨提示",message:"上传图片不符合所需的格式！",type:"warning"}),t||setTimeout(()=>{_({title:"温馨提示",message:`上传图片大小不能超过 ${s.fileSize}M！`,type:"warning"})},0),d&&t},$=async e=>{let t=new FormData;t.append("file",e.file);try{const d=s.api??A,{data:b}=await d(t);e.onSuccess(b)}catch(d){e.onError(d)}},k=E,T=(e,t)=>{e&&(t.url=e.fileUrl,k("update:fileList",n.value),g!=null&&g.prop&&(m==null||m.validateField([g.prop])),_({title:"温馨提示",message:"图片上传成功！",type:"success"}))},D=e=>{n.value=n.value.filter(t=>t.url!==e.url||t.name!==e.name),k("update:fileList",n.value)},B=()=>{_({title:"温馨提示",message:"图片上传失败，请您重新上传！",type:"error"})},L=()=>{_({title:"温馨提示",message:`当前最多只能上传 ${s.limit} 张图片，请移除后上传！`,type:"warning"})},I=w(""),l=w(!1),o=e=>{I.value=e.url,l.value=!0};return(e,t)=>{const d=u("el-icon"),b=u("ZoomIn"),V=u("Delete"),j=u("el-upload"),R=u("el-image-viewer");return f(),h("div",re,[a(j,{"file-list":n.value,"onUpdate:fileList":t[1]||(t[1]=v=>n.value=v),action:"#","list-type":"picture-card",class:M(["upload",r.value?"disabled":"",e.drag?"no-border":""]),multiple:!0,disabled:r.value,limit:e.limit,"http-request":$,"before-upload":y,"on-exceed":L,"on-success":T,"on-error":B,drag:e.drag,accept:e.fileType.join(",")},{file:p(({file:v})=>[i("img",{src:v.url,class:"upload-image"},null,8,me),i("div",{class:"upload-handle",onClick:t[0]||(t[0]=F(()=>{},["stop"]))},[i("div",{class:"handle-icon",onClick:Q=>o(v)},[a(d,null,{default:p(()=>[a(b)]),_:1}),_e],8,fe),r.value?U("",!0):(f(),h("div",{key:0,class:"handle-icon",onClick:Q=>D(v)},[a(d,null,{default:p(()=>[a(V)]),_:1}),ve],8,ge))])]),default:p(()=>[i("div",pe,[C(e.$slots,"empty",{},()=>[a(d,null,{default:p(()=>[a(le(ee))]),_:1})],!0)])]),_:3},8,["file-list","class","disabled","limit","drag","accept"]),i("div",he,[C(e.$slots,"tip",{},void 0,!0)]),l.value?(f(),N(R,{key:0,"url-list":[I.value],onClose:t[2]||(t[2]=v=>l.value=!1)},null,8,["url-list"])):U("",!0)])}}}),Ie=J(be,[["__scopeId","data-v-5c930775"]]);export{Ie as U,ke as a};