<div id="comment">

    <div class="community_post_editor_content">
		<textarea name="content" id="commentEditor">
		</textarea>
        <button type="button" v-on:click="regist" class="comment_btn"> Comment </button>
    </div>

    <div>

        <div class="comment_contents" v-for="item in commentList" >
            <div class="comment_head">
                <div class="user_profile_img_wapper" v-on:click="$router.push('/channel/'+channel.ownerId)">
                    <img v-if="item.profileImagePath && item.profileImageExt"
                         v-bind:src="'/api/profile/image/' + item.profileImagePath + '/' + item.profileImageExt"
                         class="default_user_profile_img" />
                    <img v-else src="/resources/image/kwitch_background_icon.png" class="user_profile_img" />
                </div>

                <div>{{item.writerAlias}}</div>
            </div>
            <div v-html="item.text"></div>
        </div>

    </div>

</div>