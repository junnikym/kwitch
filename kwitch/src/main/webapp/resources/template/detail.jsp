<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta charset="UTF-8">

    <link rel="stylesheet" href="//cdn.jsdelivr.net/font-nanum/1.0/nanumbarungothic/nanumbarungothic.css"/>

    <link rel="stylesheet" type="text/css" href="/resources/css/common.css" />
    <link rel="stylesheet" type="text/css" href="/resources/css/header.css" />
    <link rel="stylesheet" type="text/css" href="/resources/css/nav.css" />
    <link rel="stylesheet" type="text/css" href="/resources/css/channel.css" />

    <!-- CDN -->
    <script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
	<script src="https://unpkg.com/vue-router/dist/vue-router.js"></script>

</head>
<body>
    <%
        session = request.getSession();
        String memberId = (String)session.getAttribute("member_id");
        String memberName = (String)session.getAttribute("member_name");
        String memberAlias = (String)session.getAttribute("member_alias");
        String memberProfileImage = (String)session.getAttribute("member_profile_image");
        String memberProfileImageExt = (String)session.getAttribute("member_profile_image_ext");
    %>

    <div id="detail">

        <table class="channel_header" border=0>
            <tr>

                <td class="channel_header__profile_image" rowspan="2">

                    <div class="user_profile_img_wapper__detail">
                      <img v-bind:src="profileImageURL" class="default_user_profile_img" />
                    </div>

                    <div v-if="'<%=memberId%>' == member?.id">
                        <a class="channel_header__modify" v-on:click="toggleProfileImageSetter">
                            <img src="/resources/image/modify_icon.png" class="channel_header_setting_img" />
                        </a>

                        <div class="set_profile_image">
                            <div id="set_profile_image__thumb">
                                <img />
                            </div>
                            <div class="set_profile_image__uploader">
                                <input type="file" name="profile_image" id="upload_profile_image" @change="setProfileImageThumb"/>
                                <button class="btn"  type="button" v-on:click="toggleProfileImageSetter">cancel</button>
                                <button class="btn"  type="button" v-on:click="uploadProfileImage('<%=memberId%>')">Finish</button>
                            </div>
                        </div>
                    </div>

                </td>

                <td class="channel_header__letter" colspan="2"><h1> {{member?.alias}} </h1></td>

            </tr>

            <tr v-if="member?.id == '<%=memberId%>' && member?.ownChannelId == null">
                <td><button class="btn" type="button" v-on:click="createChannel">Create Channel</button></td>
            </tr>
            
            <tr v-if="member?.ownCommunityId != null">
            	<td class="channel_header__letter"> subscriber </td>
                <td><button class="btn" type="button" v-on:click="goToCommunity">Go Community</button></td>
            </tr>

            <tr v-else>
                <td colspan="2" class="channel_header__letter"> subscriber </td>
            </tr>

        </table>

        <div class="channel_info_content_wapper">
            <nav class="menu" id="myMenu">
                <ul>
                    <!-- active link and hover indicator, menu slider -->
                    <div class="m-active"></div>

                    <li class="channel_menu_li">
                    	<a v-on:click="changeNav('home')" id="home_btn">Home</a></li>
                    	
                    <li class="channel_menu_li">
                    	<a v-on:click="changeNav('video')" id="video_btn">Video</a></li>

                    <li>
                    	<a v-on:click="changeNav('about')" id="about_btn" class="active">About</a></li>
                    <li>
                    	<a v-on:click="changeNav('contact')" id="contact_btn" >Contact</a></li>
                </ul>
            </nav>

            <div class="channel_info_content">
                <div class="channel_home">
                    <h1> Home </h1>
                </div>

                <div class="channel_video">
                    <h1> Video </h1>
                </div>

                <div class="channel_about">
                    <h1 class="channel_alias"> About {{member?.alias}} </h1>

                    <a v-if="member?.id == '<%=memberId%>'"
                       class="channel_about__modify">
                        <img src="/resources/image/setting_icon.png" class="channel_about_setting_img" />
                    </a>

                    <p class="channel_message"> {{member?.message}} </p>
                </div>

                <div class="channel_contact">
                    <h1> Contact </h1>

                    <table>

                        <tr v-if="member?.name != null && member?.name != ''">
                            <td width='50px'> name </td>
                            <td class="channel_contact__right"> {{member?.name}} </td>
                        </tr>



                        <tr v-if="member?.email != null && member?.email != ''">
                            <td width='50px'> email </td>
                            <td class="channel_contact__right"> {{member?.email}} </td>
                        </tr>

                        <tr v-if="member?.phone != null && member?.phone != ''">
                            <td width='50px'> phone </td>
                            <td class="channel_contact__right"> {{member?.phone}} </td>
                        </tr>

                    </table>
                </div>
            </div>

        </div>
    </div>

    <script type="text/javascript" src="/resources/js/store.js"></script>
    <script type="text/javascript" src="/resources/js/util/pageHandler.js"></script>
    <script type="text/javascript" src="/resources/js/util/nav.js"></script>

    <script>
        window.onload = function() {
	        loadScript('/resources/js/view/channel.js');
        }

    </script>

</body>
</html>
