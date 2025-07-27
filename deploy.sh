#!/bin/bash

echo "🚀 Starting deployment process..."

# Build frontend
echo "📦 Building frontend..."
cd project
npm install
npm run build

if [ $? -eq 0 ]; then
    echo "✅ Frontend build successful"
else
    echo "❌ Frontend build failed"
    exit 1
fi

# Build backend
echo "🔧 Building backend..."
cd backend
mvn clean package -DskipTests

if [ $? -eq 0 ]; then
    echo "✅ Backend build successful"
else
    echo "❌ Backend build failed"
    exit 1
fi

echo "🎉 Build process completed!"
echo ""
echo "Next steps:"
echo "1. Push your code to GitHub"
echo "2. Connect your repository to Vercel"
echo "3. Deploy your Spring Boot backend to Railway/Render/Heroku"
echo "4. Set the SPRING_BOOT_URL environment variable in Vercel"
echo "5. Deploy your frontend to Vercel"
echo ""
echo "See DEPLOYMENT.md for detailed instructions." 