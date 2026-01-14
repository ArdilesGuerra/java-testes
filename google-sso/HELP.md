# Projeto: Spring Boot Google SSO (OAuth2)
Este guia documenta a implementação de Single Sign-On utilizando Java, Spring Boot e Google Cloud.

---

## ☁️ 1. Configuração no Google Cloud Console (Passo a Passo)

Siga estes links e etapas para configurar suas credenciais:

1. **Acesse o Console:** [console.cloud.google.com](https://console.cloud.google.com/)
2. **Crie um Projeto:** No topo da página, clique no seletor de projetos e em **"Novo Projeto"**.
3. **Configurar Tela de Consentimento:**
   - Acesse: [APIs e Serviços > Tela de consentimento OAuth](https://console.cloud.google.com/apis/credentials/consent)
   - Escolha **"External"** (Externo) e clique em Criar.
   - Preencha o nome do app e seu e-mail de suporte.
   - **Importante:** Se o app estiver em status "Testes", vá até a seção **"Usuários de teste"** no final da página e adicione o e-mail que você usará para logar.
4. **Criar Credenciais:**
   - Acesse: [APIs e Serviços > Credenciais](https://console.cloud.google.com/apis/credentials)
   - Clique em **"+ Criar Credenciais"** > **"ID do cliente OAuth"**.
   - Tipo de aplicativo: **Aplicativo Web**.
   - Nome: `Meu App Java SSO`.
   - **Origens JavaScript autorizadas:** `http://localhost:4000`
   - **URIs de redirecionamento autorizados:** `http://localhost:4000/login/oauth2/code/google`
5. **Obter Chaves:** Após salvar, copie o **Client ID** e o **Client Secret**.

---

## 📦 2. Dependências (pom.xml)
Configuração essencial para evitar erros de compilação e problemas de acentuação.

```xml
	<properties>
		<java.version>21</java.version>
	    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
	    <project.reporting.outputEncoding>>UTF-8</project.reporting.outputEncoding>
	</properties>
	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter</artifactId>
		</dependency>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-devtools</artifactId>
			<scope>runtime</scope>
			<optional>true</optional>
		</dependency>
		<dependency>
        	<groupId>org.springframework.boot</groupId>
        	<artifactId>spring-boot-starter-web</artifactId>
    	</dependency>
		<dependency>
        	<groupId>org.springframework.boot</groupId>
        	<artifactId>spring-boot-starter-oauth2-client</artifactId>
    	</dependency>
    	<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
		</dependency>

	</dependencies>
````

## 📦 3. Dicas para Produção (Deploy Real)
Ao subir sua aplicação para um servidor real (AWS, Heroku, Azure, etc.):

1. **Domínio:** Altere localhost:4000 para o seu domínio real (ex: https://meu-app.com) em todas as configurações do Google Cloud Console.

2. **HTTPS:** O Google exige obrigatoriamente HTTPS para redirecionamentos em produção.

3. **Variáveis de Ambiente:** Nunca deixe o Client Secret exposto no código. Utilize variáveis de ambiente no servidor e referencie no application.properties como: spring.security.oauth2.client.registration.google.client-secret=${GOOGLE_CLIENT_SECRET}

4. **Proxy/Load Balancer:** Se estiver usando Docker ou Nginx, adicione server.forward-headers-strategy=framework ao arquivo de propriedades para garantir que o redirecionamento OAuth2 use o protocolo correto (https).