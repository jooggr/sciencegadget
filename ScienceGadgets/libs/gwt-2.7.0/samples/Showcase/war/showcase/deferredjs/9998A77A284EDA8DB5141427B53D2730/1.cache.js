$wnd.showcase.runAsyncCallback1("function u0(a){var b,c;b=SC(a.a.ah(xcc),5);if(b==null){c=LC(JC(sW,1),X9b,2,4,[ycc,zcc,Acc,Bcc]);a.a.bh(xcc,c);return c}else{return b}}\nfunction v0(a){var b,c;b=SC(a.a.ah(Ccc),5);if(b==null){c=LC(JC(sW,1),X9b,2,4,['Baseball',Dcc,'Football','Hockey','Soccer',Ecc]);a.a.bh(Ccc,c);return c}else{return b}}\nfunction Vib(a){var b,c,d,e,f,g,h,i;i=new VRb;SRb(i,new tEb('<b>Select your favorite color:<\\/b>'));c=u0(a.a);for(e=0;e<c.length;e++){b=c[e];f=new PMb(iac,b);IAb(f,'cwRadioButton-color-'+b);e==2&&(Dq(f.c,true),lh(f,sh(f.hb)+'-'+Hac,true));SRb(i,f)}SRb(i,new tEb('<br><b>Select your favorite sport:<\\/b>'));h=v0(a.a);for(d=0;d<h.length;d++){g=h[d];f=new PMb('sport',g);IAb(f,'cwRadioButton-sport-'+EZb(g,' ',''));d==2&&JAb(f,(bYb(),bYb(),aYb));SRb(i,f)}return i}\nvar xcc='cwRadioButtonColors',Ccc='cwRadioButtonSports';SX(378,1,wac);_.xc=function $ib(){j_(this.b,Vib(this.a))};z7b(Cl)(1);\n//# sourceURL=showcase-1.js\n")