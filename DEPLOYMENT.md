# Deployment Guide for School Dashboard

This guide will help you deploy your React frontend and Spring Boot backend to Vercel.

## Prerequisites

1. **Vercel Account**: Sign up at [vercel.com](https://vercel.com)
2. **GitHub Account**: Your code should be in a GitHub repository
3. **Java 17**: For building the Spring Boot application

## Deployment Strategy

### Option 1: Separate Deployments (Recommended)

#### Frontend (React) - Deploy to Vercel

1. **Build the frontend**:
   ```bash
   cd project
   npm install
   npm run build
   ```

2. **Deploy to Vercel**:
   - Connect your GitHub repository to Vercel
   - Set the root directory to `project`
   - Vercel will automatically detect it as a Vite project

#### Backend (Spring Boot) - Deploy to Railway/Render/Heroku

Since Vercel doesn't support long-running Java applications, deploy your Spring Boot backend to:

- **Railway**: [railway.app](https://railway.app)
- **Render**: [render.com](https://render.com)
- **Heroku**: [heroku.com](https://heroku.com)

1. **Build the Spring Boot application**:
   ```bash
   cd project/backend
   mvn clean package
   ```

2. **Deploy to your chosen platform**

3. **Update the API URL**:
   - Set the `SPRING_BOOT_URL` environment variable in Vercel
   - Or update the API service to use the new backend URL

### Option 2: Serverless Functions (Limited)

If you want to keep everything on Vercel, you can convert your Spring Boot endpoints to serverless functions:

1. **Create API routes** in the `api/` directory
2. **Convert Spring Boot logic** to Node.js/TypeScript
3. **Use a database service** like Supabase, PlanetScale, or Vercel Postgres

## Environment Variables

Set these in your Vercel project settings:

```
SPRING_BOOT_URL=https://your-backend-url.com
```

## Database Configuration

For production, consider using:

- **Supabase**: PostgreSQL with real-time features
- **PlanetScale**: MySQL-compatible serverless database
- **Vercel Postgres**: Managed PostgreSQL

Update your `application.properties`:

```properties
# Production database configuration
spring.datasource.url=${DATABASE_URL}
spring.datasource.username=${DATABASE_USERNAME}
spring.datasource.password=${DATABASE_PASSWORD}
spring.jpa.hibernate.ddl-auto=update
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
```

## CORS Configuration

Update your Spring Boot CORS configuration in `WebConfig.java`:

```java
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("https://your-frontend-domain.vercel.app")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}
```

## Build Commands

### Frontend
```bash
npm install
npm run build
```

### Backend
```bash
mvn clean package
```

## Deployment Steps

1. **Push your code to GitHub**
2. **Connect repository to Vercel**
3. **Deploy backend to your chosen platform**
4. **Set environment variables**
5. **Deploy frontend to Vercel**

## Troubleshooting

### Common Issues

1. **CORS errors**: Ensure CORS is properly configured
2. **API not found**: Check the API base URL configuration
3. **Build failures**: Verify all dependencies are installed

### Debugging

- Check Vercel function logs
- Monitor backend application logs
- Test API endpoints directly

## Monitoring

- Use Vercel Analytics for frontend performance
- Monitor backend logs and metrics
- Set up error tracking (Sentry, etc.) 