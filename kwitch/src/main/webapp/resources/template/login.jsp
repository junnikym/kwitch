<div class="modal" id="modal-login">
	<div class="modal-bg modal-exit"></div>
	
		<div class="h_center h_center_wrapper" id="login">
		
			<div class="app form h_center_content" id="loginForm">
		
				<table border="0" class="login_stage" >
					<tr><td colspan="2" class="w_center">
						<h2> Login </h2>
					</td></tr>
		
					<tr>
						<td><h3> email </h3> </td>
						<td>
							<input class="input form__input"
								   name="email" type="text"
								   v-model="emailInput"/>
						</td>
					</tr>
		
					<tr>
						<td><h3> password </h3> </td>
						<td>
							<input class="input form__input"
								   name="password" type="password"
								   v-model="pwInput"/>
						</td>
					</tr>
		
					<tr><td colspan="2" class="w_center">
						<br/>
						<button class="btn" v-on:click="modalClose"> Back </button>
						<button class="btn" v-on:click="stageSwitch"> Login </button>
					</td></tr>
		
					<tr><td colspan="2" class="w_center">
						<br/>
						<a href="/regist">sign up</a>
						<br/>
						<br/>
						<div class="warning" v-if="wrongInput!=0" v-cloak>Incorrect username or password.</div>
					</td></tr>
		
				</table>
		
				<table class="captcha_stage">
					<tr>
						<td> <img src="/captcha/image" class="captcha_image" /> </td>
						<td>
							<button class="btn" type="button" v-on:click="resetCaptcha"> Reset </button>
						</td>
					</tr>
					<tr><td colspan="2" class="w_center">
						<input class="input form__input" type="text" onkeypress="inNumber();" v-model="captchaInput" maxlength="6"/>
					</td></tr>
					<tr><td colspan="2" class="w_center">
						<button class="btn" type="button" v-on:click="stageSwitch"> back </button>
						<button class="btn" type="button" v-on:click="checkAndLogin"> Check </button>
					</td></tr>
					<tr><td colspan="2" class="w_center">
						<div class="warning" v-if="wrongCaptcha!=0" v-cloak>Incorrect Character</div>
					</td></tr>
				</table>
		
			</div>
		
		</div>

	<button class="modal-close modal-exit" id="model_login_close" style="display: none;" type="button"></button>
</div>