<div class="liked_wrapper">
    <div class="liked_item_wrapper" v-on:click="likeToggle(false)">
        <div class="like_icon liked_item"></div>
        <div class="like_color_icon liked_color_item"></div>
    </div>

    <div class="liked_item_wrapper" v-on:click="likeToggle(true)">
        <div class="unlike_icon liked_item"></div>
        <div class="unlike_color_icon liked_color_item"></div>
    </div>
</div>