$( document ).ready(function() {
    console.log( "ready!" );
});

function getAllFilm(page, size, rating){
	$.ajax({
	    url: '/api/allFilmsByRatingViaReposCustomNativeQueryMethod?page='+page+'&rating='+rating+'&size='+size,
	    type: "get",
	    dataType: "json",
	    
	    success: function(data, textStatus, jqXHR) {
	        // since we are using jQuery, you don't need to parse response
	    	console.log(data);
	    }
	});
}


function sentRequestAction(){
	var data = {
			"natId" : "1350100204286",
			"mobileNo" : "0895264082"
		}

	console.log(hash("aesEncryptionKey"));
	
	var cipher = (function(plaintext, password) {
        passwordHash = hash(password);
        var iv = CryptoJS.enc.Hex.parse('encryptionIntVec');
        var cipher = CryptoJS.AES.encrypt(plaintext, passwordHash, {
            iv: iv,
            mode: CryptoJS.mode.CBC,
            keySize: 256 / 32,
            padding: CryptoJS.pad.Pkcs7
        });
        return cipher;
	})(JSON.stringify(data), "aesEncryptionKey");
	
//	cipherBase64 =  cipher.ciphertext.toString().hex2a().base64Encode();
	var cipherBase64 =  cipher.ciphertext.toString(CryptoJS.enc.Base64);
	console.log(cipherBase64);

}

function hash (password){
    return CryptoJS.SHA256(password);
 }
 