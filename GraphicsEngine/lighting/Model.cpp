#include <string>
#include <vector>

//assimp
#include <assimp/Importer.hpp>
#include <assimp/scene.h>
#include <assimp/postprocess.h>
//other
#include <Shader/Shader.h>
#include "Mesh.h"

class Model {
    public:
        Model(char* path) {
            loadModel(path);
        }
        void Draw(Shader& shader) {
            for (unsigned int i = 0; i < meshes.size(); i++)
                meshes[i].Draw(shader);
        }

    private:
        //model data
        vector<Mesh> meshes;
        string directory;

        vector<Texture> loadMaterialTextures(aiMaterial *material, aiTextureType type, string typeName) {
            vector<Texture> textures;
            for (unsigned int i = 0; i < material->GetTextureCount(type); i++) {
                aiString str;
                material->GetTexture(type, i, &str);
                Texture texture;
                texture.ID =
                texture.type = typeName;
                texture.path = str;
                textures.push_back(texture);
            }
            return textures;
        }

        Mesh processMesh(aiMesh* mesh, const aiScene* scene) {
            vector<Vertex> vertices;
            vector<unsigned int> indices;
            vector<Texture> textures;

            for (unsigned int i = 0; i < mesh->mNumVertices; i++) {
                Vertex vertex;
                //process vertex positions, normals and texture coordinates
                glm::vec3 vector;
                //positions
                vector.x = mesh->mVertices[i].x;
                vector.y = mesh->mVertices[i].y;
                vector.z = mesh->mVertices[i].z;
                vertex.position = vector;
                //normals
                vector.x = mesh->mNormals[i].x;
                vector.y = mesh->mNormals[i].y;
                vector.z = mesh->mNormals[i].z;
                vertex.normal = vector;
                //texture coordinates
                if (mesh->mTextureCoords[0]) {
                    glm::vec2 vec;
                    vec.x = mesh->mTextureCoords[0][i].x;
                    vec.y = mesh->mTextureCoords[0][i].y;
                    vertex.texCoords = vec;
                } else {
                    vertex.texCoords = glm::vec2(0.0f, 0.0f);
                }
                vertices.push_back(vertex);
            }
            //process indices
            for (unsigned int i = 0; i < mesh->mNumFaces; i++) {
                aiFace face = mesh->mFaces[i];
                for (unsigned int j = 0; j < face.mNumIndices; j++)
                    indices.push_back(face.mIndices[j]);
            }
            //process material
            if (mesh->mMaterialIndex >= 0) {
                aiMaterial* material = scene->mMaterials[mesh->mMaterialIndex];
                vector<Texture> diffuseMaps = loadMaterialTextures(*material);
            }
        }

        void processNode(aiNode* node, const aiScene* scene) {
            for (unsigned int i = 0; i < node->mNumMeshes; i++) {
                aiMesh* mesh = scene->mMeshes[node->mMeshes[i]];
                meshes.push_back(processMesh(mesh, scene));
            }
        }

        void loadModel(string path) {
            Assimp::Importer import;
            const aiScene* scene = import.ReadFile(path, aiProcess_Triangulate | aiProcess_FlipUVs);

            if (!scene || scene->mFlags & AI_SCENE_FLAGS_INCOMPLETE || !scene->mRootNode) {
                cout << "ERROR::ASSIMP::" << import.GetErrorString() << endl;
                return;
            }
            directory = path.substr(0, path.find_last_of('/'));

            processNode(scene->mRootNode, scene);
        }
        vector<Texture> loadMaterialTextures(aiMaterial);

        
};