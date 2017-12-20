$(document).ready(function(){
    var lang = $("body").attr("lang");

    $("#j-btc-invest-btn").click(function() {
        if (!$(this).hasClass("segmentItem_selected")) {
            $("#j-ltc-invest-btn").removeClass("segmentItem_selected")
            $("#j-eth-invest-btn").removeClass("segmentItem_selected")
            $("#j-btc-invest-btn").addClass("segmentItem_selected")
            $("#j-btc-invest-data").show();
            $("#j-ltc-invest-data").hide();
            $("#j-eth-invest-data").hide();
            ajaxBtcSaveList(1);
        }
    });
    $("#j-ltc-invest-btn").click(function() {
        if (!$(this).hasClass("segmentItem_selected")) {
            $("#j-btc-invest-btn").removeClass("segmentItem_selected")
            $("#j-eth-invest-btn").removeClass("segmentItem_selected")
            $("#j-ltc-invest-btn").addClass("segmentItem_selected")
            $("#j-ltc-invest-data").show();
            $("#j-btc-invest-data").hide();
            $("#j-eth-invest-data").hide();
            ajaxLtcSaveList(1);
        }
    });
    $("#j-eth-invest-btn").click(function() {
        if (!$(this).hasClass("segmentItem_selected")) {
            $("#j-btc-invest-btn").removeClass("segmentItem_selected")
            $("#j-ltc-invest-btn").removeClass("segmentItem_selected")
            $("#j-eth-invest-btn").addClass("segmentItem_selected")
            $("#j-eth-invest-data").show();
            $("#j-btc-invest-data").hide();
            $("#j-ltc-invest-data").hide();
        }
    });

    function btcShortSaveShow() {
        $("#j-btc-short-save").removeClass("hide").addClass("show");
        $("#j-mask").addClass("show");
        $("#j-btc-short-copy").zclip({
            path: "/static/swf/ZeroClipboard.swf",
            copy: function() {
                return $("#j-btc-short-addr").val();
            },
            afterCopy: function() {
                if (lang == 'cn') {
                    alert("已复制");
                } else {
                    alert("Copied");
                }
            }
        });
    };

    function btcShortSaveHide() {
        $("#j-btc-short-save").removeClass("show").addClass("hide");
        $("#j-mask").removeClass("show");
        $("#j-btc-short-copy").zclip("remove");
    };

    function ltcShortSaveShow() {
        $("#j-ltc-short-save").removeClass("hide").addClass("show");
        $("#j-mask").addClass("show");
        $("#j-ltc-short-copy").zclip({
            path: "/static/swf/ZeroClipboard.swf",
            copy: function() {
                return $("#j-ltc-short-addr").val();
            },
            afterCopy: function() {
                if (lang == 'cn') {
                    alert("已复制");
                } else {
                    alert("Copied");
                }
            }
        });
    };

    function ltcShortSaveHide() {
        $("#j-ltc-short-save").removeClass("show").addClass("hide");
        $("#j-mask").removeClass("show");
        $("#j-ltc-short-copy").zclip("remove");
    };

    function ethShortSaveShow() {
        $("#j-eth-short-save").removeClass("hide").addClass("show");
        $("#j-mask").addClass("show");
        $("#j-eth-short-copy").zclip({
            path: "/static/swf/ZeroClipboard.swf",
            copy: function() {
                return $("#j-eth-short-addr").val();
            },
            afterCopy: function() {
                if (lang == 'cn') {
                    alert("已复制");
                } else {
                    alert("Copied");
                }
            }
        });
    };

    function ethShortSaveHide() {
        $("#j-eth-short-save").removeClass("show").addClass("hide");
        $("#j-mask").removeClass("show");
        $("#j-eth-short-copy").zclip("remove");
    };

    $("#j-btc-short-invest-btn").click(btcShortSaveShow);
    $("#j-btc-short-save-close").click(btcShortSaveHide);
    $("#j-ltc-short-invest-btn").click(ltcShortSaveShow);
    $("#j-ltc-short-save-close").click(ltcShortSaveHide);
    $("#j-eth-short-invest-btn").click(ethShortSaveShow);
    $("#j-eth-short-save-close").click(ethShortSaveHide);

    function btcFixedSaveShow() {
        $("#j-btc-fixed-save").removeClass("hide").addClass("show");
        $("#j-mask").addClass("show");
        $("#j-btc-fixed-copy").zclip({
            path: "/static/swf/ZeroClipboard.swf",
            copy: function() {
                return $("#j-btc-fixed-addr").val();
            },
            afterCopy: function() {
                if (lang == 'cn') {
                    alert("已复制");
                } else {
                    alert("Copied");
                }
            }
        });
    };

    function btcFixedSaveHide() {
        $("#j-btc-fixed-save").removeClass("show").addClass("hide");
        $("#j-mask").removeClass("show");
        $("#j-btc-fixed-copy").zclip("remove");
    };

    function ltcFixedSaveShow() {
        $("#j-ltc-fixed-save").removeClass("hide").addClass("show");
        $("#j-mask").addClass("show");
        $("#j-ltc-fixed-copy").zclip({
            path: "/static/swf/ZeroClipboard.swf",
            copy: function() {
                return $("#j-ltc-fixed-addr").val();
            },
            afterCopy: function() {
                if (lang == 'cn') {
                    alert("已复制");
                } else {
                    alert("Copied");
                }
            }
        });
    };

    function ltcFixedSaveHide() {
        $("#j-ltc-fixed-save").removeClass("show").addClass("hide");
        $("#j-mask").removeClass("show");
        $("#j-ltc-fixed-copy").zclip("remove");
    };

    $("#j-btc-fixed-invest-btn").click(btcFixedSaveShow);
    $("#j-btc-fixed-save-close").click(btcFixedSaveHide);
    $("#j-ltc-fixed-invest-btn").click(ltcFixedSaveShow);
    $("#j-ltc-fixed-save-close").click(ltcFixedSaveHide);

    $(".j-fund-coin-tab").click(function() {
        if (!$(this).parent().hasClass("tabbarItem_current")) {
            $(this).parent().parent().find(".j-turn-coin-tab").first().parent().removeClass("tabbarItem_current");
            $(this).parent().addClass("tabbarItem_current");
            $(this).parent().parent().parent().find(".mod_demandToRegular").first().hide();
            $(this).parent().parent().parent().find(".mod_topUp").first().show();
        }
    });

    $(".j-turn-coin-tab").click(function() {
        if (!$(this).parent().hasClass("tabbarItem_current")) {
            $(this).parent().parent().find(".j-fund-coin-tab").first().parent().removeClass("tabbarItem_current");
            $(this).parent().addClass("tabbarItem_current");
            $(this).parent().parent().parent().find(".mod_topUp").first().hide();
            $(this).parent().parent().parent().find(".mod_demandToRegular").first().show();
        }
    });

    function transSuccessShow() {
        $("#j-trans-success").removeClass("hide").addClass("show");
        $("#j-mask").addClass("show");
    };

    function transSuccessHide() {
        $("#j-trans-success").removeClass("show").addClass("hide");
        $("#j-mask").removeClass("show");
        window.location.reload();
    };

    $("#j-trans-success-ok").click(transSuccessHide);
    $("#j-trans-success-close").click(transSuccessHide);

    $("#j-btc-trans-btn").click(function() {
        var btn = $(this);
        if (btn.attr("processing")) {
            return;
        }

        var amount = $("#j-btc-trans-amount").val();
        if (amount.length == 0) {
            if (lang == 'cn') {
                alert("请输入数量");
            } else {
                alert("Please input amount");
            }
            return;
        }
        var money_pswd = $("#j-btc-money-pass").val();
        if (money_pswd.length == 0) {
            if (lang == 'cn') {
                alert("请输入资金密码");
            } else {
                alert("Please input asset password");
            }
            return;
        }
        btn.addClass("b_btn1_disable");
        btn.attr("processing", "true");
        $.ajax({
            url: '/api/short_to_fixed/',
            data: {
                contract  : 'btc_fixed_30',
                amount    : amount,
                money_pswd: money_pswd
            },
            dataType:"json",
            type:"post",
            error: function() {
                console.log("error");
                btn.removeClass("b_btn1_disable");
                btn.removeAttr("processing");
            },
            success: function(data) {
                switch (data.Code) {
                case "0":
                    btcFixedSaveHide();
                    transSuccessShow();
                    break;
                case "20001":
                    if (lang == 'cn') {
                        alert("余额不足");
                    }else{
                        alert("balance not enough");
                    }
                    break;  
                case "20003":
                    if (lang == 'cn') {
                        alert("没有设置资金密码");
                    }else{
                        alert("have not set money password");
                    }
                    break; 
                case "20004":
                    if (lang == 'cn') {
                        alert("资金密码错误");
                    }else{
                        alert("money password wrong.");
                    }
                    break;            
                default:
                    if (lang == "cn"){
                        alert("其他错误");
                    } else {
                         alert("other mistake");
                    }
                    break;
                }
                $("#j-btc-trans-amount").val("");
                $("#j-btc-money-pass").val("");
                btn.removeClass("b_btn1_disable");
                btn.removeAttr("processing");
            }
        })
    });

    $("#j-ltc-trans-btn").click(function() {
        var btn = $(this);
        if (btn.attr("processing")) {
            return;
        }

        var amount = $("#j-ltc-trans-amount").val();
        if (amount.length == 0) {
            if (lang == 'cn') {
                alert("请输入数量");
            } else {
                alert("Please input amount");
            }
            return;
        }
        var money_pswd = $("#j-ltc-money-pass").val();
        if (money_pswd.length == 0) {
            if (lang == 'cn') {
                alert("请输入资金密码");
            } else {
                alert("Please input asset password");
            }
            return;
        }
        btn.addClass("b_btn1_disable");
        btn.attr("processing", "true");
        $.ajax({
            url: '/api/short_to_fixed/',
            data: {
                contract  : 'ltc_fixed_30',
                amount    : amount,
                money_pswd: money_pswd
            },
            dataType:"json",
            type:"post",
            error: function() {
                console.log("error");
                btn.removeClass("b_btn1_disable");
                btn.removeAttr("processing");
            },
            success: function(data) {
                switch (data.Code) {
                case "0":
                    ltcFixedSaveHide();
                    transSuccessShow();
                    break;
                case "20001":
                    if (lang == 'cn') {
                        alert("余额不足");
                    }else{
                        alert("balance not enough");
                    }
                    break;  
                case "20003":
                    if (lang == 'cn') {
                        alert("没有设置资金密码");
                    }else{
                        alert("have not set money password");
                    }
                    break; 
                case "20004":
                    if (lang == 'cn') {
                        alert("资金密码错误");
                    }else{
                        alert("money password wrong.");
                    }
                    break;            
                default:
                    if (lang == "cn"){
                        alert("其他错误");
                    } else {
                         alert("other mistake");
                    }
                    break;
                }
                $("#j-ltc-trans-amount").val("");
                $("#j-ltc-money-pass").val("");
                btn.removeClass("b_btn1_disable");
                btn.removeAttr("processing");
            }
        })
    });

    function fixedToShortShow() {
        $("#j-fixed-to-short-box").removeClass("hide").addClass("show");
        $("#j-mask").addClass("show");
    };

    function fixedToShortHide() {
        $("#j-fixed-to-short-box").removeClass("show").addClass("hide");
        $("#j-mask").removeClass("show");
    };

    function fixedToShortSuccShow() {
        $("#j-fixed-to-short-success").removeClass("hide").addClass("show");
        $("#j-mask").addClass("show");
    };

    function fixedToShortSuccHide() {
        $("#j-fixed-to-short-success").removeClass("show").addClass("hide");
        $("#j-mask").removeClass("show");
        window.location.reload();
    };

    $("#j-fixed-to-short-close").click(fixedToShortHide);
    $("#j-fixed-to-short-success-ok").click(fixedToShortSuccHide);
    $("#j-fixed-to-short-success-close").click(fixedToShortSuccHide);

    function FixedToShort(btn, item) {
        $("#j-fixed-save-amount").html(btn.parent().parent().find(".order-amount").first().html() + ' ' +  item);
        $("#j-fixed-to-short-unit").html(item);
        $("#j-fixed-to-short-confirm").attr("order_id", btn.attr("order_id"));
        fixedToShortShow();
    };

    $("#j-fixed-to-short-confirm").click(function() {
        var btn = $(this);
        if (btn.attr("processing")) {
            return;
        }

        var amount = $("#j-fixed-to-short-amount").val();
        if (amount.length == 0) {
            if (lang == 'cn') {
                alert("请输入数量");
            } else {
                alert("Please input amount");
            }
            return;
        }
        var money_pswd = $("#j-fixed-to-short-pass").val();
        if (money_pswd.length == 0) {
            if (lang == 'cn') {
                alert("请输入资金密码");
            } else {
                alert("Please input asset password");
            }
            return;
        }

        btn.addClass("b_btn1_disable");
        btn.attr("processing", "true");

        $.ajax({
            url: '/api/fixed_to_short/',
            data: {
                order_id  : btn.attr("order_id"),
                amount    : amount,
                money_pswd: money_pswd
            },
            dataType:"json",
            type:"post",
            error: function() {
                console.log("error");
                btn.removeClass("b_btn_disable");
                btn.removeAttr("processing");
            },
            success: function(data) {
                switch (data.Code) {
                case "0":
                    fixedToShortHide();
                    fixedToShortSuccShow();
                    break;
                case "20001":
                    alert("余额不足");

                    break;  
                case "20003":
                    alert("没有设置资金密码");
                    break; 
                case "20004":
                    alert("资金密码错误");
                    break;
                case "30009":
                    alert("订单状态错误");
                    break;  
                case "30010":
                    alert("订单余额不足");
                    break; 
                case "30011":
                    alert("充值尚未完成");
                    break;            
                default:
                if (lang == "cn"){
                    alert("其他错误");
                } else {
                     alert("other mistake");
                }
                    break;
                }
                 fixedToShortHide();
                
                $("#j-fixed-to-short-amount").val("");
                $("#j-fixed-to-short-pass").val("");
                btn.removeClass("b_btn_disable");
                btn.removeAttr("processing");
            }
        })
    });

    function updateBtcSaveList(curr, total) {
        $("#j-btc-save-curr").val(curr);
        $("#j-btc-save-total").html(total);
        if (curr == 1) {
            $("#j-btc-save-prev").addClass("disable");
        } else {
            $("#j-btc-save-prev").removeClass("disable");
        }
        if (curr == total) {
            $("#j-btc-save-next").addClass("disable");
        } else {
            $("#j-btc-save-next").removeClass("disable");
        }
    };

    function renderBtcSaveList(data) {
        $("#j-btc-save-list tr:gt(0)").remove();
        for (var i = 0; i < data.length; ++i) {
            var row = '<tr>';
            row += '<td>' + data[i].date + '</td>';
            row += '<td><em class="order-amount">' + data[i].amount + '</em></td>';
            row += '<td>' + data[i].end_date + '</td>';
            row += '<td><em>' + data[i].profit + '</em></td>';
            if (data[i].status == 'completed') {
                if (lang == 'cn') {
                    row += '<td><a href="javascript:void(0)" order_id="' + data[i].id + '" class="j-btc-fixed-to-short">转活期</a></td>';
                } else {
                    row += '<td><a href="javascript:void(0)" order_id="' + data[i].id + '" class="j-btc-fixed-to-short">Convert</a></td>';
                }
            } else {
                if (lang == 'cn') {
                    row += '<td>已结束</td>';
                } else {
                    row += '<td>already finished</td>';
                }
            }
            row += '</tr>';
            $("#j-btc-save-list tr:last").after(row);
        }

        for (var i = data.length; i < 6; ++i) {
            var row = '<tr>';
            row += '<td></td>';
            row += '<td></td>';
            row += '<td></td>';
            row += '<td></td>';
            row += '<td></td>';
            row += '</tr>';
            $("#j-btc-save-list tr:last").after(row);
        }

        $(".j-btc-fixed-to-short").click(function() {
            FixedToShort($(this), 'BTC');
        });
    };

    function ajaxBtcSaveList(page) {
        $.ajax({
            url: '/api/usersaveorderlist',
            data: {
                contract: 'btc_fixed_30',
                page: page
            },
            dataType:"json",
            type:"get",
            error: function() {
                console.log("error");
            },
            success: function(data) {
                result = data;
                updateBtcSaveList(result.current_page, result.total_page);
                renderBtcSaveList(result.data);
            }
        });
    };
    
    function updateLtcSaveList(curr, total) {
        $("#j-ltc-save-curr").val(curr);
        $("#j-ltc-save-total").html(total);
        if (curr == 1) {
            $("#j-ltc-save-prev").addClass("disable");
        } else {
            $("#j-ltc-save-prev").removeClass("disable");
        }
        if (curr == total) {
            $("#j-ltc-save-next").addClass("disable");
        } else {
            $("#j-ltc-save-next").removeClass("disable");
        }
    };

    function renderLtcSaveList(data) {
        $("#j-ltc-save-list tr:gt(0)").remove();
        for (var i = 0; i < data.length; ++i) {
            var row = '<tr>';
            row += '<td>' + data[i].date + '</td>';
            row += '<td><em class="order-amount">' + data[i].amount + '</em></td>';
            row += '<td>' + data[i].end_date + '</td>';
            row += '<td><em>' + data[i].profit + '</em></td>';
            if (data[i].status == 'completed') {
                if (lang == 'cn') {
                    row += '<td><a href="javascript:void(0)" order_id="' + data[i].id + '" class="j-ltc-fixed-to-short">转活期</a></td>';
                } else {
                    row += '<td><a href="javascript:void(0)" order_id="' + data[i].id + '" class="j-ltc-fixed-to-short">Convert</a></td>';
                }
            } else {
                if (lang == 'cn') {
                    row += '<td>已结束</td>';
                } else {
                    row += '<td>already finished</td>';
                }
            }
            row += '</tr>';
            $("#j-ltc-save-list tr:last").after(row);
        }

        for (var i = data.length; i < 6; ++i) {
            var row = '<tr>';
            row += '<td></td>';
            row += '<td></td>';
            row += '<td></td>';
            row += '<td></td>';
            row += '<td></td>';
            row += '</tr>';
            $("#j-ltc-save-list tr:last").after(row);
        }

        $(".j-ltc-fixed-to-short").click(function() {
            FixedToShort($(this), 'LTC');
        });
    };

    function ajaxLtcSaveList(page) {
        $.ajax({
            url: '/api/usersaveorderlist',
            data: {
                contract: 'ltc_fixed_30',
                page: page
            },
            dataType:"json",
            type:"get",
            error: function() {
                console.log("error");
            },
            success: function(data) {
                result = data;
                updateLtcSaveList(result.current_page, result.total_page);
                renderLtcSaveList(result.data);
            }
        });
    };

    $("#j-btc-save-prev").click(function() {
        var curr = $("#j-btc-save-curr").val();
        if (curr > 1) {
            ajaxBtcSaveList(parseInt(curr) - 1);
        }
    });

    $("#j-btc-save-next").click(function() {
        var curr = $("#j-btc-save-curr").val();
        var total = $("#j-btc-save-total").html();
        if (curr < total) {
            ajaxBtcSaveList(parseInt(curr) + 1);
        }
    });

    $("#j-btc-save-curr").keydown(function(event) {
        if (event.which == "0XD" || event.keyCode == 13) {
            ajaxBtcSaveList(parseInt($("#j-btc-save-curr").val()));
        }
    });

    $("#j-ltc-save-prev").click(function() {
        var curr = $("#j-ltc-save-curr").val();
        if (curr > 1) {
            ajaxLtcSaveList(parseInt(curr) - 1);
        }
    });

    $("#j-ltc-save-next").click(function() {
        var curr = $("#j-ltc-save-curr").val();
        var total = $("#j-ltc-save-total").html();
        if (curr < total) {
            ajaxLtcSaveList(parseInt(curr) + 1);
        }
    });

    $("#j-ltc-save-curr").keydown(function(event) {
        if (event.which == "0XD" || event.keyCode == 13) {
            ajaxLtcSaveList(parseInt($("#j-ltc-save-curr").val()));
        }
    });

    ajaxBtcSaveList(1);
});
