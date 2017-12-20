$(function(){
	//input
	$('.m_inputBox input,.m_inputBox4 input,.m_inputBox5 input').focus(function(){
		$(this).parent().addClass('m_inputBox_focus')
	});
	$('.m_inputBox input,.m_inputBox4 input,.m_inputBox5 input').blur(function(){
		$(this).parent().removeClass('m_inputBox_focus')
	})

	//input2
	$('.m_inputBox2 .unit').hover(function(){
		$(this).siblings('.dropMenu').fadeIn();
	})
	$('.m_inputBox2 .dropMenu').hover(function(){},function(){
		$(this).fadeOut();
	})
	$('.m_inputBox2 .dropMenu li').click(function(){
		$(this).siblings().removeClass('dropMenuItem_current');
		$(this).addClass('dropMenuItem_current')
		var value = $(this).text();
		$(this).parent().siblings('.unit').find('.unitValue').text(value);
	})

	$('.f_hd .accountNameBox').hover(function(){
		$('.hd_dropMenu').fadeIn(200);
	})
	$('.hd_dropMenu').hover(function(){
		$('.hd_dropMenu').fadeIn(200);
	},function(){
		$('.hd_dropMenu').fadeOut(200);
	})

	$('.f_topAd .i_topAdClose').click(function(){
		$('.f_topAd').hide()
	})

	//input with country code 
	$('.m_inputBox_tel .countryCode').click(function(){
		$(this).parent().find('.countryList').toggle();
	})

	//select
	$('.m_inputBox6 .dropDownTrigger').click(function(){
		$(this).siblings('.selectDropDown').toggle()
	})
	$('.m_inputBox6 .selectItem a').click(function(){
		var text = $(this).text();
		$(this).parents('.selectDropDown').hide();
		$(this).parents('.selectDropDown').siblings('.dropDownTrigger').find('.selectedValue').attr({"value":text})
		$(this).parents('.selectDropDown').siblings('.dropDownTrigger').find('.selectedValue').change();
	})
})