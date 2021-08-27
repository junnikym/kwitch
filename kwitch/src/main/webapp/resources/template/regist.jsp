
<form class="form" id="registForm"
			  v-on:submit="onSubmit">
			  
	<div class="app">
		<div class="regist_stage">
		
			<table border="0">
			
				<thead>
       				<tr><th colspan="2"><h1 class="regist_h"> SignUp</h1><hr/></th></tr>
       			</thead>
       			
	
				<tr>
					<td><h3> email </h3> </td>
					<td>
						<input class="input form__input" id="regist"
							   name="email" type="email"
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
				
				<tr>
					<td><h3> name </h3> </td>
					<td>
						<input class="input form__input"
							   name="name" type="text"
							   v-model="nameInput"/>
					</td>
				</tr>
				
				<tr>
					<td><h3> alias </h3> </td>
					<td>
						<input class="input form__input"
							   name="alias" type="text"
							   v-model="aliasInput"/>
					</td>
				</tr>
				
				<tr>
					<td><h3> phone </h3> </td>
					<td>
						<div class="w_center">
							<b class="form__phone_input"> 010 &nbsp; - &nbsp; </b>
							<input class="input form__input form__phone_input w_center"
								   name="phone_mid" type="text"
								   v-model="phoneMidInput"/>
							<b> &nbsp; - &nbsp; </b>
							<input class="input form__input form__phone_input w_center"
								   name="phone_last" type="text"
								   v-model="phoneLastInput"/>
						</div>
					</td>
				</tr>
				<tr><td colspan='2' class='hr_light'><hr/></td></tr>
				
				<tr>
					<td><h3> birth </h3> </td>
					<td>
						<select name='birthYear' v-model="birthYearInput"
								class="input form__input from__birth_input w_center">
							
							<option	v-bind:value="{ number:'year' }" selected>{{(new Date()).getFullYear()}}</option>
							<option v-for="year in getYearRange()"
									v-bind:value="{ number:'year' }">
									{{year}}</option>		 
						</select>
						<b> &nbsp; Year &nbsp; </b>
						
						<select name='birthYear' v-model="birthMonthInput"
								class="input form__input from__birth_input w_center">
							<option v-bind:value="{ number:'01' }" selected>1</option>
							<option v-for="month in getMonthRange()"
									v-bind:value="{ number:'month'  }">{{month}}</option>
						</select>
						<b> &nbsp; Month &nbsp; </b>
						
						<select name='birthYear' v-model="birthDayInput"
								class="input form__input from__birth_input w_center">
							<option v-bind:value="{ number:'01' }" selected>1</option>
							<option v-for="day in getDayRange()"
									v-bind:value="{ number:'day' }">{{day}}</option>		 
						</select>
						<b> &nbsp; Day &nbsp; </b>
					</td>
				</tr>
				
				<tr><td colspan='2' class='hr_light'><br/></td></tr>
	
				<tr><td colspan="2" class="w_center">
					<button class="btn" v-on:click="back"> Not Now </button>
					<button class="btn" type="submit"> Submit </button>
				</td></tr>
	
			</table>
	
		</div>

		<div class="setting_stage">
			<thead>
       				<tr><th colspan="2"><h1 class="regist_h"> Set Profile </h1><hr/></th></tr>
       			</thead>
			<input type="file" name="profile_image" id="upload_profile_image"/>
			<button class="btn"  type="submit">Finish</button>

		</div>
	</div>
	
</form>
