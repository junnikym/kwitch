<div id="channelSimpleList">

    <div class="user_profile_img_wapper" v-on:click="$router.push('/channel/'+channel.ownerId)">
        <img v-if="channel.profileImagePath && channel.profileImageExt"
             v-bind:src="'/api/profile/image/' + channel.profileImagePath + '/' + channel.profileImageExt"
             class="default_user_profile_img" />
        <img v-else src="/resources/image/kwitch_background_icon.png" class="user_profile_img" />
    </div>

    <h3 v-on:click="$router.push('/channel/'+channel.ownerId)"> {{channel.alias}} </h3>

</div>