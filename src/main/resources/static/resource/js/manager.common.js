var CONST = {
	/**
	 * 도메인 설정
	 */
	DOMAIN : {
		//API : 'https://api.adminez.co.kr/cool',
		API : 'http://localhost/',
		WEB : 'http://localhost'
	},
	PAGE : 1,
	PAGE_ROWS : 15,
	PAGE_ROWS_P : 5,
	LOGIN_ID : sessionStorage.getItem('id'),
	LOGIN_EMAIL : sessionStorage.getItem('email'),
	LOGIN_NO : sessionStorage.getItem('no'),
	PRODUCT_NO : 0,
	NUM : 0,
	ARR : [],
	S_ARR : [],
	SV : {},
	OB : ''
};

var Common = {
	/* 모바일 확인 */
	isMobile : function() {
		var user = navigator.userAgent;
		var is_mobile = false; 
		if( user.indexOf("iPhone") > -1 || user.indexOf("Android") > -1 ) {
			is_mobile = true; 
		} 
		return is_mobile; 
	},
	/* 화폐 단위 콤마 */
	numberWithCommas : function(price) {
	    return price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ",");
	},
	
	/* replaceAll */
	replaceAll : function(value,org, dest) {
		return value.split(org).join(dest);	
	},
	
	isNotNull : function(value1, value2) {
		var result = false;
		if(value1 == undefined || value1 == null || value1 == "") {
			window.Android.toastAlert('고객명을 입력해주세요.');
		} else {
			if(value2 == undefined || value2 == null || value2 == "") {
				window.Android.toastAlert('상담 내용을 입력해주세요.');
			} else {
				result = true;
			}
		}
		return result;
		
	},
	/* 전화번호 하이폰 추가 */
	blue : function(num) {
		var tmp = '';
		
		if(num.length==11) { //010-1234-1234
			tmp += num.substr(0,3);
			tmp += '-';
			tmp += num.substr(3,4);
			tmp += '-';
			tmp += num.substr(7);	
		}else { //02-1234-1234
			tmp += num.substr(0,2);
			tmp += '-';
			tmp += num.substr(2,4);
			tmp += '-';
			tmp += num.substr(6);
		}
		
		return tmp;
	},
	/**
	 *  yyyy-MM-dd 포맷으로 반환
	 */
	getFormatDate : function(d,str){
		let date = new Date();
		if(d != "") date = new Date(d);

	    let year = date.getFullYear();              //yyyy
	    let month = (1 + date.getMonth());          //M
	    month = month >= 10 ? month : '0' + month;  //month 두자리로 저장
	    let day = date.getDate();                   //d
	    day = day >= 10 ? day : '0' + day;          //day 두자리로 저장
		return  year + str + month + str + day;    
	},
	/* 현재 날짜 */
	getDate1 : function() {
		var d = new Date(); // for now
		var year = d.getFullYear();
	    var month = (1 + d.getMonth());
	    month = month >= 10 ? month : '0' + month;
	    var day = d.getDate();
	    day = day >= 10 ? day : '0' + day;
        
        var datetext = year + "." + month + "." + day;
        
		return datetext;
	},
	/* 현재 날짜 value */
	getDate2 : function(date) {
		var d = new Date(date); // for now
		var year = d.getFullYear();
	    var month = (1 + d.getMonth());
	    month = month >= 10 ? month : '0' + month;
	    var day = d.getDate();
	    day = day >= 10 ? day : '0' + day;
        
        var datetext = year + "." + month + "." + day;
        
		return datetext;
	},
	/* 선택한 곳으로 스크롤 이동 */
	fnMove : function(seq) {
		var offset = $(seq).offset();
		$('html, body').animate({
			scrollTop : offset.top - 30
		}, 400/*스크롤시간*/);
	},	
	getPaging : function( pagingArea, totalCount, callback )
	{
		var totalPage  = Math.floor( 1+( parseInt( totalCount )-1 ) / CONST.PAGE_ROWS );
		var calPage = Math.floor( ( CONST.PAGE %10 ) -1 );
		if( calPage == -1 ){
			calPage = 9;
		}
		var startPage = CONST.PAGE - calPage;
		var maxPage = totalPage > (startPage+totalPage-1)?(startPage+totalPage-1):totalPage;
		var endPage = startPage + 9;
		
		if( maxPage < startPage + 9 )
		{
			endPage = maxPage;
		}
		
		if( totalCount == undefined )
		{
			endPage = 0;
		}
		
		/*console.log('totalPage ' + totalPage);
		console.log('calPage ' + calPage);
		console.log('startPage ' + startPage);
		console.log('maxPage ' + maxPage);
		console.log('endPage ' + endPage);*/
		
		$( '#' + pagingArea ).empty().append(
				$( '<span>' ).addClass( 'arrow' ).append(
					$( '<em>' ).addClass( 'first' ).text( '처음페이지' ).click( function(){
						if( CONST.PAGE > 1 ) {
							CONST.PAGE = 1;
							callback();
						}
					})
				).append(
					$( '<em>' ).addClass( 'prev' ).text( '이전페이지' ).click( function(){
						if( CONST.PAGE > 1 ) {
							CONST.PAGE--;
							callback();
						}
					})
				)
		).append(
				$( '<span>' ).addClass( 'number' ).append(
					function(){
						for( var i=startPage; i<=endPage; i++ ){
							$( this ).append(
								$( '<a>' ).text(i).append(
									function(){
										if( CONST.PAGE == i )
										{
											$( this ).addClass( 'selected' );
										}
									}
								).click( function(){
									CONST.PAGE = $( this ).text();
									callback();
								})
							);
						}
					}
				)
		).append(
				$( '<span>' ).addClass( 'arrow' ).append(
						$( '<em>' ).addClass( 'next' ).text( '다음페이지' ).click( function(){
							if( CONST.PAGE < totalPage )
							{
								CONST.PAGE++;
								callback();
							}
						} )
				).append(
						$( '<em>' ).addClass( 'last' ).text( '마지막페이지' ).click( function(){
							if( CONST.PAGE < totalPage )
							{
								CONST.PAGE = totalPage;
								callback();
							}
						} )
				)
		);
	},
	getUserList : function( callback )
	{
		var startrow = 0;
		console.log(CONST.PAGE);
		startrow = ( parseInt( CONST.PAGE ) -1 ) * parseInt( CONST.PAGE_ROWS );
		console.log(startrow);
		var param = {};
		param['work'] = 'userList';
		param['keyword'] = $( '#keyword' ).val().replace(/-/g, "");
		param['count'] = CONST.PAGE_ROWS;
		param['startrow'] = startrow;
		
		console.log(param);
		Common.sendAjax('/common/admin', param, 'POST', true, function(json) {
			if (json.status.code != 200) {
				alert('네트워크 에러1 ' + json.status);
				return false;
			} else {
				try {
					console.log(json);
					
					if( json.data != null ) { // json 데이터가 있을때
						var list = json.data.list;
						var totalCount = list[0].totalCnt;
						var tr,td;
						
						Common.getPaging('pagingArea', totalCount, function(){
							Common.getUserList();
						});
						
						$( '#userList' ).empty();
						var startNum = totalCount - startrow;
						
						for(var i=0;i<list.length;i++) {
							var data = list[i];
							if((i+1)%2==1){
								tr = $('<tr class="even">');
							}else {
								tr = $('<tr >').attr({'no' : data.no});	
							}
							
							tr.append($('<td>').html(data.no));
							tr.append($('<td>').html(data.name));
							tr.append($('<td>').html(data.email));
							tr.append($('<td>').html(data.phone));
							tr.append($('<td>').html(data.department));
							tr.append($('<td>').html(data.rank));
							tr.click(function(){
								var no = $( this ).attr('no');
								//location.href = 'http://salestool.hifec.co.kr/adminUserDetail.html?no=' + no;
							});
							
							$('#userList').append(tr);
						}
					}
					
				} catch (Excpetion) {
					console.log('네트워크 에러2 ' + Excpetion);
				}
			}
		});
	},
	/* admin 페이지 페이지 이동시 네비 변경 */
	navConfig : function( navId )
	{		
		$( '#' + navId ).addClass( 'active' ).parent().parent().addClass( 'active' );
	},
	/**
	 * ajax 통신
	 */
	sendAjax : function( url, param, method, async, callback )
	{
		var sendUrl = '';
		
		if( url.indexOf( 'http' ) > -1 )
		{
			sendUrl = url;
		}
		else
		{
			sendUrl = CONST.DOMAIN.API + url;
		}

		/*var today = new Date();
		
		var year = today.getFullYear(); // 년도
		var month = today.getMonth() + 1;  // 월
		var date = today.getDate();  // 날짜
		
		var hours = today.getHours(); // 시
		var minutes = today.getMinutes();  // 분
		var seconds = today.getSeconds();  // 초
		
		if(Number(month)<10){month = '0'+month;}
		if(Number(date)<10){date = '0'+date;}
		if(Number(hours)<10){hours = '0'+hours;}
		if(Number(minutes)<10){minutes = '0'+minutes;}
		if(Number(seconds)<10){seconds = '0'+seconds;}
		
		param['currentTime'] = year + '/' + month + '/' + date + ' ' + hours + ':' + minutes + ':' + seconds;
		
		if( url.indexOf( 'admin' ) > -1 || sessionStorage.getItem('login') != null) {
			param['currentUser'] = sessionStorage.getItem('login');
			param['currentApi'] = url.split('/common')[1]+'/'+param['work'];
		}else {
			param['phone'] = phone;
			param['currentApi'] = 'APP'+url.split('/common')[1]+'/'+param['work'];
		}*/
			
		param = JSON.stringify( param ); //rowdata 사용시
		//console.log(param);
		//console.log(url);
		$.support.cors = true;
		$.ajax({
			type: method,
			async: false,
			cache: false,
			dataType: 'json',
			contentType : 'application/json',
			data: param,
			url: sendUrl,
			error:function(request,status,error){
//					console.log(request);
//					Common.endLoading();
			},
			beforeSend: function(request) 
			{
//					console.log(request);
//					Common.startLoading();
			},
			success: function(res)
			{
				callback( res );
//					Common.endLoading();
			}
		});
		
	},

	sendAjaxMail : function( param, callback )
	{
		$.support.cors = true;
		$.ajax({
			type: 'POST',
			async: true,
			cache: false,
			dataType: 'json',
			data: param,
			url: 'http://mail.aedstore.co.kr/sendCertify',
			error:function(request,status,error){
				console.log(error);
				Common.endLoading();
			},
			beforeSend: function() 
			{
				Common.startLoading();
			},
			success: function(res)
			{
				Common.endLoading();
				callback();
			}
		});
		
	},
	
	sendAjax2 : function( url, param, method, callback )
	{
		var sendUrl = '';
		
		if( url.indexOf( 'http' ) > -1 )
		{
			sendUrl = url;
		}
		else
		{
			sendUrl = CONST.DOMAIN.API + url;
		}

		$.support.cors = true;
		$.ajax({
			url: sendUrl,
        	data: param,
        	processData: false,
        	contentType: false,
        	dataType: 'json',
			type: method,
			error:function(request,status,error){
					callback( request );
//					console.log(request);
//					Common.endLoading();
			},
			beforeSend: function(request) 
			{
//					console.log(request);
//					Common.startLoading();
			},
			success: function(res)
			{
				callback( res );
//					Common.endLoading();
			}
		});
		
	},
	
	/**
	 * alert창 공통 함수
	 * @param msg
	 */
	systemAlert : function( msg, callback )
	{
//			alert( msg );
		$( 'body' ).toast( msg );
		if( callback != null )
		{
			callback();
		}
	},

	/**
	 * confirm창 공통 함수
	 * @param msg
	 * @returns {Boolean}
	 */
	systemConfirm : function( msg )
	{
		if( confirm( msg ) )
		{
			return true;
		}
		else
		{
			return false;
		}
	},
	
	touchEvent : function( targetObj, color, preColor )
	{
		targetObj.mouseout( function(){				
			$( this ).css( { 'background': preColor } );				
		} ).bind('touchstart', function(e) {
			$( this ).css( { 'background': color } );
		}).bind('touchend', function(e) {
			$( this ).css( { 'background': preColor } );
		}).bind('touchmove', function(e) {
			$( this ).css( { 'background': preColor } );
		});
	},
	
	touchEventImage : function( targetObj, id, imageUrl, overImageUrl )
	{
		targetObj.mouseout( function(){
			$( '#' + id ).attr( { 'src': imageUrl } );
		} ).bind('touchstart', function(e) {
			$( '#' + id ).attr( { 'src': overImageUrl } );
		}).bind('touchend', function(e) {
			$( '#' + id ).attr( { 'src': imageUrl } );
		}).bind('touchmove', function(e) {
			$( '#' + id ).attr( { 'src': imageUrl } );
		});
	},
	
	touchEventText : function( targetObj, color )
	{
		targetObj.mouseout( function(){
			$( this ).css( { 'color': '' } );
		} ).bind('touchstart', function(e) {
			$( this ).css( { 'color': color } );
		}).bind('touchend', function(e) {
			$( this ).css( { 'color': '' } );
		}).bind('touchmove', function(e) {
			$( this ).css( { 'color': '' } );
		});
	},
	
	touchEventText2 : function( targetObj, color )
	{
		targetObj.mouseout( function(){
			$( this ).css( { 'color': '' } );
		} ).bind('touchstart', function(e) {
			$( this ).css( { 'color': color } );
		}).bind('touchend', function(e) {
			$( this ).css( { 'color': '' } );
		}).bind('touchmove', function(e) {
			$( this ).css( { 'color': '' } );
		});
	}
};