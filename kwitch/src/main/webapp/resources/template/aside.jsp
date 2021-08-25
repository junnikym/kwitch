<div id="aside" class="aside">
    <button id="aside_open_btn" class="page_move_icon aside_open_btn hidden" v-on:click="foldAside"></button>

    <div class="aside_wapper">

        <button class="page_move_icon" v-on:click="foldAside"></button>
        <h3 class="aside_header"> subscribed </h3>
        <div>
            <channel-small-item-component
                v-for="item in subscribedChannelList"
                v-bind:channel="item"></channel-small-item-component>
        </div>
    </div>
</div>