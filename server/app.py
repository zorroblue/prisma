from flask import Flask, render_template, jsonify, request, send_file
from PIL import Image
from neural_style.neural_style import stylize
import StringIO


app = Flask(__name__)
MODEL_DIR = 'saved-models/'


def serve_pil_image(pil_img):
    img_io = StringIO.StringIO()
    pil_img.save(img_io, 'JPEG', quality=70)
    img_io.seek(0)
    return send_file(img_io, mimetype='image/jpeg')


@app.route('/upload', methods=['POST'])
def check_image():
    file = request.files['image']
    img = Image.open(file.stream)
    print "Received"
    output = stylize(img, model= MODEL_DIR+'udnie.pth', cuda=1)
    #output.show()
    #return jsonify(status='success')
    return serve_pil_image(output)

@app.errorhandler(404)
def not_found(error):
    return render_template('404.html'), 404

if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000)