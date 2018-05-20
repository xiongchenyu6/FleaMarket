function generatePDF(id) {
    html2canvas(document.getElementById("ID" +id)).then(function(canvas) {
            var imgData = canvas.toDataURL('image/png')
            var doc = jsPDF('p','mm');
            doc.addImage(imgData,'PNG',0,0,canvas.width,canvas.height);
            doc.save('test.pdf');
    })
}
