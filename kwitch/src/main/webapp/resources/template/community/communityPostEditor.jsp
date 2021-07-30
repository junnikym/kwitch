
<div id="communityPostEditor">
	<table class="editor_title">
		<tr>
			<td class="editor_title_latter"><h3>title</h3> </td>
			<td class="editor_title_input">
				<input type="text" class="form__input editor" v-model="postContent.title"/> </td>

			<td class="editor_setting_latter"><h3>menu</h3></td>
			
			<td class="editor_setting_input">
				<select class="input form__input from__birth_input w_center"
						v-model="selectedMenuId">
					<option v-for="menu in menuList" v-bind:value="menu.id">{{menu.title}}</option>
				</select>
			</td>
		</tr>
	</table>

	<div class="community_post_editor_content">			
		<textarea name="content" id="editor">
			{{postContent.content}}
		</textarea>
		<p>
			<button type="button" id="back" class="btn" v-on:click="$router.go(-1)"> Back </button>
			<button type="button" v-on:click="uploadPost" id="submitPost" class="btn"> Upload </button>
		</p>
	</div>
</div>