FROM python:3.8-buster
COPY requirements.txt /tmp

RUN apt update
RUN pip3 install --upgrade pip
RUN pip install -r /tmp/requirements.txt

RUN mkdir /booking

VOLUME /booking

WORKDIR /booking/

CMD ["python","pomhotel-booking-app.py"]
#CMD ["bash"]