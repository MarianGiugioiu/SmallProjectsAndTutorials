using System;
using System.Collections.Generic;
using System.ComponentModel;
using System.Data;
using System.Drawing;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows.Forms;
namespace Shapes
{
    public partial class Form1 : Form
    {
        void drawCircle(float x, float y, float r, Graphics G)
        {
            Pen myPen = new Pen(Color.Black);
            Brush myBrush = new SolidBrush(Color.Red);
            G.DrawEllipse(myPen, x - r, y - r, r + r, r + r);
            if (r > 20)
            {
                drawCircle(x + r / 2, y, r / 2,G);
                drawCircle(x - r / 2, y, r / 2,G);
                drawCircle(x, y + r / 2, r / 2, G);
                drawCircle(x, y - r / 2, r / 2, G);
            }
        }
        public Form1()
        {
            InitializeComponent();
            this.Height = 500;
            this.Width = 500;
        }

        private void Form1_Paint(object sender, PaintEventArgs e)
        {
            Graphics g = e.Graphics;
            Pen myPen = new Pen(Color.Black);
            Brush myBrush = new SolidBrush(Color.Red);
            Point x = new Point(200, 200);
            Point y = new Point(400, 100);
            g.DrawLine(myPen, x, y);
            //g.DrawEllipse(myPen, 200, 200, 100, 100);
            drawCircle(200, 200, 200, g);
        }
    }
    
}
