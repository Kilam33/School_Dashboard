# School Dashboard Backend

A Spring Boot REST API backend for the School TV Dashboard application.

## Features

- **RESTful API** with GET and POST endpoints for dashboard data
- **H2 In-Memory Database** for development (easily switchable to MySQL/PostgreSQL)
- **Data Validation** with Bean Validation annotations
- **CORS Configuration** for frontend integration
- **Exception Handling** with global exception handler
- **JPA/Hibernate** for data persistence
- **Auto-initialization** with default data

## API Endpoints

### GET /api/dashboard
Retrieves current dashboard data including:
- Staff attendance (present/absent counts)
- Student attendance (present/absent counts)
- Behavior reports (middle school and elementary)
- Upcoming events
- School days left countdown
- Last updated timestamp

### POST /api/dashboard
Updates dashboard data based on update type:
- `staff` - Update staff attendance
- `student` - Update student attendance
- `behavior` - Update behavior reports
- `events` - Update upcoming events list
- `daysLeft` - Update school days countdown

### GET /api/health
Health check endpoint

## Quick Start

1. **Prerequisites**
   - Java 17 or higher
   - Maven 3.6+

2. **Run the application**
   ```bash
   cd backend
   mvn spring-boot:run
   ```

3. **Access the API**
   - API Base URL: `http://localhost:8080/api`
   - H2 Console: `http://localhost:8080/h2-console`
     - JDBC URL: `jdbc:h2:mem:schooldb`
     - Username: `sa`
     - Password: `password`

## Configuration

### Database
The application uses H2 in-memory database by default. To switch to MySQL or PostgreSQL:

1. Add the appropriate database dependency to `pom.xml`
2. Update `application.properties` with your database configuration
3. Remove `spring.jpa.hibernate.ddl-auto=create-drop` for production

### CORS
CORS is configured to allow requests from:
- `http://localhost:5173` (Vite dev server)
- `http://localhost:3000` (Create React App)
- `http://127.0.0.1:5173`

Update `WebConfig.java` to add additional origins as needed.

## Data Models

### DashboardData
Main entity containing all dashboard metrics with embedded attendance and behavior data.

### AttendanceData
Embeddable class for present/absent counts with validation.

### BehaviorReport
Contains behavior data for both middle school and elementary levels.

### Event
Entity for upcoming events with title, date, time, and display order.

## Development

### Testing the API
Use curl, Postman, or any HTTP client:

```bash
# Get dashboard data
curl http://localhost:8080/api/dashboard

# Update staff attendance
curl -X POST http://localhost:8080/api/dashboard \
  -H "Content-Type: application/json" \
  -d '{"type":"staff","data":{"present":45,"absent":2}}'
```

### Database Console
Access H2 console at `http://localhost:8080/h2-console` to view and modify data directly.

## Production Deployment

1. **Build the application**
   ```bash
   mvn clean package
   ```

2. **Run the JAR**
   ```bash
   java -jar target/dashboard-backend-0.0.1-SNAPSHOT.jar
   ```

3. **Environment Variables**
   Set production database configuration via environment variables or external `application.properties`.

## Integration with Frontend

The backend is designed to work seamlessly with the React frontend. Update the frontend's API base URL to point to your deployed backend:

```javascript
const API_BASE_URL = 'http://your-backend-url:8080/api';
```

## Future Enhancements

- Role-based authentication and authorization
- WebSocket support for real-time updates
- Audit logging for data changes
- Database migration scripts
- Docker containerization
- Monitoring and health checks