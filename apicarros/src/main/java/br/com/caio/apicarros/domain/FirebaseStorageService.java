package br.com.caio.apicarros.domain;

import java.io.IOException;
import java.io.InputStream;
import java.util.Base64;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Service;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Acl;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.Bucket;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.StorageClient;

import br.com.caio.apicarros.api.upload.UploadInput;

@Service
public class FirebaseStorageService {

	@PostConstruct
	private void init() throws IOException {

		if (FirebaseApp.getApps().isEmpty()) {
			InputStream in = FirebaseStorageService.class.getResourceAsStream("/serviceAccountKey.json");

			System.out.println(in);

			FirebaseOptions options = new FirebaseOptions.Builder()
					.setCredentials(GoogleCredentials.fromStream(in))
					.setStorageBucket("api-carros-546de.appspot.com")
					.setDatabaseUrl("https://api-carros-546de.firebaseio.com")
					.build();

			FirebaseApp.initializeApp(options);
		}
	}

	public String upload(UploadInput uploadInput) {
		
		//CRIAR PARA GRAVAR NO CLOUD DO GOOGLE
		Bucket bucket = StorageClient.getInstance().bucket();
		
		//TRANSFORMA A IMAGEM RECEBIDA EM BASE64 PARA BYTES
		byte[] bytes = Base64.getDecoder().decode(uploadInput.getBase64());

		//PEGA O NOME DO ARQUIVO
		String fileName = uploadInput.getFileName();
		
		//CRIA O ARQUIVO LA NO STORAGE
		Blob blob = bucket.create(fileName, bytes, uploadInput.getMimeType());

		///DEIXA URL PUBLICA
		blob.createAcl(Acl.of(Acl.User.ofAllUsers(), Acl.Role.READER));

		return String.format("https://storage.googleapis.com/%s/%s", bucket.getName(), fileName);
	}
}
