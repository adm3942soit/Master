function popup(adress, width, height)
{
    window.open(adress, 'popD', 'toolbar=no,menubar=no,resizable=no,scrollbars=no,status=no,location=no,width=' + width + ',height=' + height);
}


function popup(adress, width, height, rsz, scrb)
{
    window.open(adress, 'popD', 'toolbar=no,menubar=no,resizable=' + rsz + ',scrollbars=' + scrb + ',status=no,location=no,width=' + width + ',height=' + height);
}

function clearRadios(radio)
{
    var form = radio.form;
    var elemets = form.elements;
    if(elemets!=null)
    {
    for (i = 0; i < elemets.length; i++)
    {
        if(elemets[i]!=null)
        {
        if (elemets[i].type == "radio")
        {
            var element = elemets[i];

            if (element!=null)
            {
               if(element.checked)
               {
                 element.checked = false;
               }
            }
        }
        }
    }
    }
    if(radio!=null) radio.checked = true;
}

function closeWindow()
{
    ww = window.open(window.location, "_self");
    if(ww!=null)
    {
     ww.close();
    }
}

function tabPanel_showTab(tab)
{
    for (i = 0; i <= 10; i++)
    {
        var p = document.getElementById("panel" + i)
        var t = document.getElementById("tab" + i)
        if (p != null) p.style.display = "none";
        if (t != null) t.style.background = "#ddd";
    }
    var p = document.getElementById("panel" + tab);
    if (p != null) p.style.display = "block";
    var d = document.getElementById("tab" + tab);
    if (d != null) d.style.background = "white";

}
function showApplet(idApp)
{
    var doc=document.getElementById(idApp);
    var app=document.applets[0];
    var app1=document.applets[1];
    if(app!=null)
    {
      app.style.display = "block";
      app.style.background="white";
      app.focus();
    }
    if(app1!=null)
    {
      app1.style.display = "block";
      app1.style.background="white";
      app1.focus();
    }

}
function invisibleApplet(idApp)
{

    var doc=document.getElementById(idApp);
    var app=doc.applets[0];

    if(app!=null)
    {
     app.width=0;
     app.height=0;
     app.style.display = "none";
     app.style.background="white";
     app.focus();
    }

}

function panel_showTab(tab)
{
    for (i = 0; i < 10; i++)
    {
        var p = document.getElementById("panel" + i)
        var t = document.getElementById("tab" + i)
        if (p != null) p.style.display = "none";
        if (t != null) t.style.display = "none";
    }
    var p =document.getElementById("panel" + tab); 
    if(p!=null)p.style.display = "block";

}
function yesNo(evt)
{
    if (evt)
        ;
    else if (window.event)
        evt = window.event;
    else if (event)
        evt = event;
    else
        return true;

    if (evt.charCode)
        keycode = evt.charCode;
    else if (evt.keyCode)
        keycode = evt.keyCode;
    else if (evt.which)
        keycode = evt.which;
    else
        keycode = 0;

    if (keycode == 78) {//N
        var b = document.getElementById('YNForm:noButton');
        if (b!=null) b.click();
       return false;
    }
    if (keycode == 89) {//y
        var b = document.getElementById('YNForm:yesButton');
        if (b!=null) b.click();
       return true;

    }

}
function trapEnter(evt) {
    var keycode;

    if (evt)
        ;
    else if (window.event)
        evt = window.event;
    else if (event)
        evt = event;
    else
        return true;

    if (evt.charCode)
        keycode = evt.charCode;
    else if (evt.keyCode)
        keycode = evt.keyCode;
    else if (evt.which)
        keycode = evt.which;
    else
        keycode = 0;

    if (keycode == 13) {
        var b = document.getElementById('findForm:enterButton');
        if (b!=null) b.click();
        return false;
    }
    else
        return true;
}

function showalert(ind)
{
     alert(ind);
}
/*
document.getElementById = function(id)
	{
		var elem = document.nativeGetElementById(id);
		if(elem)
		{
			//make sure that it is a valid match on id
			if(elem.attributes['id'].value == id)
			{
				return elem;
			}
			else
			{
				//otherwise find the correct element
				for(var i=1;i<document.all[id].length;i++)
				{
					if(document.all[id][i].attributes['id'].value == id)
					{
						return document.all[id][i];
					}
				}
			}
		}
		return null;
	};
*/
function setFocus(parm)
{
//  var IE = (navigator.appName=="Microsoft Internet Explorer");
//  if(IE)
//  {
  var d=document.getElementById(parm);
  if(d!=null)
  d.focus();
//  }
}
function setFocusOnNextElement(param)
{
    var elem=document.getElementById(param);
    if(elem==null)return;
/*elem = XXXX.nextSibling;
while(elem.innerHTML == null)
{
elem = elem.nextSibling;
}*/
    var nextElem = elem.nextSibling;
    if(nextElem)setFocus(nextElem);
}
function setFocusOnPrevElement(param)
{
// setFocus(Object.prototype.prevObject);
    var elem=document.getElementById(param);
    if(elem==null)return;
    var prevElem = elem.previousObject;
    if(prevElem)setFocus(prevElem);

}
function getTextFromApplet(inputField)
{
  var d=document.getElementById(inputField);
  if(d!=null)
  {
   d.value=document.applets[0].getBodyLetter();
  }
}

function hide(element)
{
  var d=document.getElementById(element);
  if(d!=null) d.style.display = 'none';

}
function show(element)
{
    var d=document.getElementById(element);
    if(d!=null){ d.show(); d.style.display = 'block';}
}
function confDialog(message)
{
   var ret=window.confirm(message);
 return ret;
}
function submitWithConfirm(param)
{
    var d=document.getElementById(param);
    var answer=confDialog();
    if(d!=null )
    { //???????
      if(answer) d.submit();
    }

}
function submit(param)
{
    var d=document.getElementById(param);
    if(d!=null)
    d.submit();
    
}
function getLocale()
{
  if (navigator.appName == 'Netscape')
       var language = navigator.language;
  else
       var language = navigator.browserLanguage;

  return language;
}
Object.prototype.nextObject = function() {
var n = this;
do n = n.nextSibling;
while (n && n.nodeType != 1);
return n;
}

Object.prototype.previousObject = function() {
var p = this;
do p = p.previousSibling;
while (p && p.nodeType != 1);
return p;
}
function setTextToApplet(inputField)
{
  var d=document.getElementById(inputField);
  if(d!=null)
  {
   document.applets[0].setBodyLetter(d.value);
  }
}
   function receiveMail()
   {
 document.applets[1].receiveMail(false);
 document.applets[1].focus();
   }
   function sendMail(param)
   {
 var d=document.getElementById(param);
 setTextToApplet(param);
 if(d!=null)document.applets[0].sendMessage(d.value);
 else document.applets[0].sendMessage(null);
 document.applets[0].focus();
   }
function changeLabel(tabId) {
    var tabLabel = event.target || event.srcElement;
    tabLabel.innerHTML = 'Loading...';
}
function dataTableSelectOneRadio(radio) {
    var radioId = radio.name.substring(radio.name.lastIndexOf(':'));

    for (var i = 0; i < radio.form.elements.length; i++) {
        var element = radio.form.elements[i];

        if (element.name.substring(element.name.lastIndexOf(':')) == radioId) {
            element.checked = false;
        }
    }

    radio.checked = true;
}
/*
function updatePicture()
{
    document.getElementById("viewH").update();
}*/
