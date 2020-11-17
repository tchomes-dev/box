#include <string>
#include <map>
#include <vector>

#include <iostream>
#include <fstream>

using namespace std;

class Movie {
public:
    Movie(const string name, const int rating);
    bool read(const string fileName);
    friend Movie operator<<(ostream& out, Movie& movie);

private:
    map<const string, vector<int>> movieMap;
};

//constructors
Movie::Movie(const string name, const int rating) {
    read("HW07.txt");
    vector<int> ratings;
    ratings.push_back(rating);
    this->movieMap.insert(make_pair(name, ratings));
}

bool Movie::read(const string fileName) {
    int rating;

    ofstream file(fileName);
    if (file.is_open()) {

    }
}

double averageRating(vector<int> ratings) {
    double average = 0.0;
    for (int rating : ratings){
        average += (double)rating;
    }
    return average;
}

/*
Computing III -- COMP.2010 Honor Statement
The practice of good ethical behavior is essential for maintaining
good order in the classroom, providing an enriching learning
experience for students, and as training as a practicing computing
professional upon graduation. This practice is manifested in the
University�s Academic Integrity policy. Students are expected to
strictly avoid academic dishonesty and adhere to the Academic
Integrity policy as outlined in the course catalog. Violations will
be dealt with as outlined therein.
All programming assignments in this class are to be done by the
student alone. No outside help is permitted except the instructor and
approved tutors.
I certify that the work submitted with this assignment is mine and was
generated in a manner consistent with this document, the course
academic policy on the course website on Blackboard, and the UMass
Lowell academic code.
Date:   10-29-2020
Name:   Tony Choma
*/