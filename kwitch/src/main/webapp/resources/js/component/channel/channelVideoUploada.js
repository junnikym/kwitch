const channelVideoUploadComponent = {
	template: channelVideoUploadTemplate,
	data() { return {
		file: undefined,
		dropArea: undefined,
		dragText: undefined,
		origin: undefined,
		videoTitle: '',
		videoText: '',
	}},
	methods: {

		modalClose: function() {
		    parent.document.getElementById('model_video_upload_close').click();
		    this.reset();
	    },
	    
	    upload: function() {
			if(!this.file) {
				alert("need to select video file");
				return;
			}
			else if(!this.videoTitle) {
				alert("empty video title");
				return;
			}

	    	const formData = new FormData();
	    	formData.append('video', this.file);
		    formData.append('title', this.videoTitle);
		    formData.append('videoText', this.videoText);
        	
	    	fetch('/api/video/upload', {
				method: 'POST',
				body: formData
    		})
            .then(res=>{
            })
            .catch(err => console.log(err))

		    this.modalClose();
	    },

		showFile: function(){
			let fileType = this.file.type; //getting selected file type
			let validExtensions = ["video/m4v", "video/avi", "video/mpg", "video/mp4", "video/x-matroska"]; //adding some valid image extensions in array
			
			console.log("type is ", fileType);

			if(validExtensions.includes(fileType)){ //if user selected file is an image file
				let fileReader = new FileReader(); //creating new FileReader object
				fileReader.onload = ()=>{
					let fileURL = fileReader.result; //passing user file source in fileURL variable
					// UNCOMMENT THIS BELOW LINE. I GOT AN ERROR WHILE UPLOADING THIS POST SO I COMMENTED IT
					const videoTag = `<video src="${fileURL}" alt="video"></video> <a class="x_button" v-on:click="reset">x</a>`; //creating an img tag and passing user selected file source inside src attribute
					document.querySelector(".drag-area-text").innerHTML = videoTag; //adding that created img tag inside dropArea container
				}
				fileReader.readAsDataURL(this.file);
			}
			else{
				alert("This is not an Video File!");
				this.dropArea.classList.remove("active");
				this.dragText.textContent = "Drag & Drop to Upload File";
			}
		},
		
		reset: function() {
			document.querySelector(".drag-area").innerHTML = this.origin;
			console.log(this.origin);
			this.setEvents();
		},
		
		setEvents: function() {
			
			this.dropArea	= document.querySelector(".drag-area");
			this.dragText 	= this.dropArea.querySelector(".drag-text");
			const button 	= this.dropArea.querySelector(".upload-button");
			const input 	= this.dropArea.querySelector(".upload-input");

			button.onclick = ()=>{
				input.click(); //if user click on the button then the input also clicked
			}
			
			console.log(this.dropArea.classList);

			input.addEventListener("change", ()=>{
				//getting user select file and [0] this means if user select multiple files then we'll select only the first one
				this.file = input.files[0];
				this.dropArea.classList.add("active");
				this.showFile(); //calling function
			});

			//If user Drag File Over DropArea
			this.dropArea.addEventListener("dragover", (event)=>{
				event.preventDefault(); //preventing from default behaviour
				this.dropArea.classList.add("active");
				this.dragText.textContent = "Release to Upload File";
			});

			//If user leave dragged File from DropArea
			this.dropArea.addEventListener("dragleave", ()=>{
				this.dropArea.classList.remove("active");
				this.dragText.textContent = "Drag & Drop to Upload File";
			});

			//If user drop File on DropArea
			this.dropArea.addEventListener("drop", (event)=>{
				event.preventDefault(); //preventing from default behaviour
				//getting user select file and [0] this means if user select multiple files then we'll select only the first one
				this.file = event.dataTransfer.files[0];
				this.showFile(); //calling function
			});
		}

	},
	mounted() {
		//selecting all required elements
		this.dropArea	= document.querySelector(".drag-area");
		this.origin		= this.dropArea.innerHTML;
		
		this.setEvents();
	}
};