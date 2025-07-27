# Quick Start: Deploy to Vercel

## Immediate Steps

### 1. Frontend Deployment (Vercel)

1. **Push your code to GitHub**
   ```bash
   git add .
   git commit -m "Prepare for Vercel deployment"
   git push origin main
   ```

2. **Deploy to Vercel**
   - Go to [vercel.com](https://vercel.com)
   - Sign up/Login with GitHub
   - Click "New Project"
   - Import your GitHub repository
   - Set root directory to `project`
   - Click "Deploy"

### 2. Backend Deployment (Railway - Recommended)

1. **Go to Railway**
   - Visit [railway.app](https://railway.app)
   - Sign up with GitHub

2. **Deploy Spring Boot**
   - Click "New Project"
   - Select "Deploy from GitHub repo"
   - Choose your repository
   - Set root directory to `project/backend`
   - Railway will auto-detect it's a Java project

3. **Get your backend URL**
   - Railway will provide a URL like: `https://your-app.railway.app`

### 3. Connect Frontend to Backend

1. **Set Environment Variable in Vercel**
   - Go to your Vercel project settings
   - Add environment variable:
     - Name: `SPRING_BOOT_URL`
     - Value: `https://your-app.railway.app`

2. **Redeploy**
   - Vercel will automatically redeploy with the new environment variable

## Alternative Backend Platforms

If Railway doesn't work, try:

### Render
- [render.com](https://render.com)
- Free tier available
- Good for Spring Boot apps

### Heroku
- [heroku.com](https://heroku.com)
- Requires credit card for verification
- Excellent for Spring Boot

## Testing Your Deployment

1. **Frontend**: Visit your Vercel URL
2. **Backend**: Test API endpoints directly
3. **Integration**: Check if frontend can fetch data from backend

## Common Issues & Solutions

### CORS Errors
- Update your Spring Boot CORS configuration
- Add your Vercel domain to allowed origins

### API Not Found
- Check the `SPRING_BOOT_URL` environment variable
- Verify the backend is running

### Build Failures
- Check the build logs in Vercel
- Ensure all dependencies are in `package.json`

## Next Steps

After successful deployment:
1. Set up a custom domain
2. Configure SSL certificates
3. Set up monitoring and analytics
4. Consider using a production database

See `DEPLOYMENT.md` for detailed instructions. 