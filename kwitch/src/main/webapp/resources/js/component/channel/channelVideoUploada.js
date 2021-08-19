const channelVideoUploadComponent = {
	template: channelVideoUploadTemplate,
	data() { return {
		file: undefined,
		dropArea: undefined,
		dragText: undefined,
	}},
	methods: {


		showFile: function(){
			let fileType = this.file.type; //getting selected file type
			let validExtensions = ["image/jpeg", "image/jpg", "image/png"]; //adding some valid image extensions in array

			if(validExtensions.includes(fileType)){ //if user selected file is an image file
				let fileReader = new FileReader(); //creating new FileReader object
				fileReader.onload = ()=>{
					let fileURL = fileReader.result; //passing user file source in fileURL variable
					// UNCOMMENT THIS BELOW LINE. I GOT AN ERROR WHILE UPLOADING THIS POST SO I COMMENTED IT
					const imgTag = `<img src="${fileURL}" alt="image">`; //creating an img tag and passing user selected file source inside src attribute
					this.dropArea.innerHTML = imgTag; //adding that created img tag inside dropArea container
				}
				fileReader.readAsDataURL(this.file);
			}
			else{
				alert("This is not an Image File!");
				this.dropArea.classList.remove("active");
				this.dragText.textContent = "Drag & Drop to Upload File";
			}
		},

	},
	mounted() {
		//selecting all required elements
		this.dropArea  = document.querySelector(".drag-area");
		this.dragText    = this.dropArea.querySelector(".drag-text");
		const button      = this.dropArea.querySelector(".upload-button");
		const input       = this.dropArea.querySelector(".upload-input");

		button.onclick = ()=>{
			input.click(); //if user click on the button then the input also clicked
		}

		input.addEventListener("change", function(){
			//getting user select file and [0] this means if user select multiple files then we'll select only the first one
			this.file = this.files[0];
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
};